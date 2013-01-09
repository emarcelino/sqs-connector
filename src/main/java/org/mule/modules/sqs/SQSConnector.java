/**
 * Mule SQS Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sqs;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.partition;
import static com.google.common.io.Closeables.closeQuietly;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.lang.Thread.interrupted;
import static org.jclouds.sqs.SQS.receiveAllAtRate;
import static org.jclouds.sqs.features.Messages.toReceiptHandle;
import static org.jclouds.sqs.options.ReceiveMessageOptions.Builder.attribute;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jclouds.sqs.domain.Message;
import org.jclouds.sqs.features.MessageApi;
import org.jclouds.sqs.features.QueueApi;
import org.jclouds.sqs.options.ReceiveMessageOptions;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Source;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.api.callback.SourceCallback;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Lists;

/**
 * Amazon Simple Queue Service (Amazon SQS) is a distributed queue messaging
 * service introduced by Amazon.com in April of 2006. It supports programmatic
 * sending of messages via web service applications as a way to communicate over
 * the internet. The intent of SQS is to provide a highly scalable hosted
 * message queue that resolves issues arising from the common producer-consumer
 * problem or connectivity between producer and consumer.
 * <p/>
 * This connector does not provide a method for creating a queue. The reason
 * being that it will automatically create it when its needed instead of having
 * to manually specify so.
 *
 * @author MuleSoft, Inc.
 */
@Connector(name = "sqs", friendlyName = "Amazon SQS")
public class SQSConnector {

    @VisibleForTesting
    final SQSConnection.Builder connectionBuilder;

    public SQSConnector(SQSConnection.Builder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public SQSConnector() {
        this(AWSSQSConnection.builder());
    }

    // managed by connect/disconnect
    @VisibleForTesting
    transient SQSConnection connection;
    @VisibleForTesting
    transient QueueApi queueApi;
    @VisibleForTesting
    transient URI queue;
    @VisibleForTesting
    transient MessageApi messageApi;

    /**
     * The Region of the SQS connection
     */
    @Configurable
    @Optional
    private String region;

    /**
     * AWS access id
     */
    @Configurable
    private String accessKey;

    /**
     * AWS secret access id
     */
    @Configurable
    private String secretKey;

    /**
     * @param queueName
     *            The name of the queue to connect to
     * @throws ConnectionException
     *             If a connection cannot be made
     */
    @Connect
    public void connect(@ConnectionKey String queueName) throws ConnectionException {
        try {

            this.connection = connectionBuilder.credentials(accessKey, secretKey).region(region).build();
            this.queueApi = connection.queueApi();

            this.queue = getOrCreateQueue(queueName);
            this.messageApi = connection.messageApiForQueue(queue);

        } catch (RuntimeException e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, null, e.getMessage(), e);
        }
    }

    private URI getOrCreateQueue(String queueName) {
        URI queue = queueApi.get(queueName);
        if (queue == null) {
            queue = queueApi.create(queueName);
        }
        return queue;
    }

    @Disconnect
    public void disconnect() {
        closeQuietly(connection);
        connection = null;
        queueApi = null;
        messageApi = null;
        queue = null;
    }

    @ValidateConnection
    public boolean isConnected() {
        return connection != null && this.queue != null && connection.valid();
    }

    @ConnectionIdentifier
    public String getMessageQueueUrl() {
        return this.queue.toString();
    }

    /**
     * Sends a message to a specified queue. The message must be between 1 and
     * 256K bytes long.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:send-message}
     *
     * @param message
     *            the message to send. Defaults to the payload of the Mule
     *            message.
     */
    @Processor
    public void sendMessage(@Optional @Default("#[payload]") final String message) {
        messageApi.send(message);
    }

    /**
     * Attempts to receive a message from the queue. Every attribute of the
     * incoming message will be added as an inbound property. Also the following
     * properties will also be added:
     * <p/>
     * sqs.message.id = containing the message identification
     * sqs.message.receipt.handle = containing the message identification
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample
     * sqs:receive-messages}
     *
     * @param callback
     *            Callback to call when a new message is available.
     * @param visibilityTimeout
     *            the duration (in seconds) the retrieved message is hidden from
     *            subsequent calls to retrieve.
     * @param preserveMessages
     *            Flag that indicates if you want to preserve the messages in
     *            the queue. False by default, so the messages are going to be
     *            deleted.
     * @param pollPeriod
     *            time in milliseconds to wait between polls (when no messages
     *            were retrieved). Default period is 1000 ms.
     * @param numberOfMessages
     *            the number of messages to be retrieved on each call (10
     *            messages max). By default, 1 message will be retrieved.
     */
    @Source
    public void receiveMessages(SourceCallback callback,
            @Optional @Default("30") Integer visibilityTimeout,
            @Optional @Default("false") Boolean preserveMessages,
            @Optional @Default("1000") Long pollPeriod,
            @Optional @Default("1") Integer numberOfMessages) {

        ReceiveMessageOptions options = attribute("All").visibilityTimeout(visibilityTimeout);

        while (!interrupted()) {

            if (numberOfMessages == 1) {
                receiveAndProcessMessage(callback, options, preserveMessages);
            } else {
                receiveAndProcessMessages(numberOfMessages, callback, options, preserveMessages);
            }

            sleepUninterruptibly(pollPeriod, TimeUnit.MILLISECONDS);
        }
    }

    private void receiveAndProcessMessages(int numberOfMessages, SourceCallback callback,
            ReceiveMessageOptions options, boolean preserveMessages) {
        List<Message> messages = receiveAllAtRateAndPreserve(numberOfMessages, options, preserveMessages);
        process(messages, callback);
    }

    private void receiveAndProcessMessage(SourceCallback callback, ReceiveMessageOptions options,
            Boolean preserveMessages) {
        com.google.common.base.Optional<Message> message = receiveAndPreserve(options, preserveMessages);
        if (message.isPresent())
            process(message.get(), callback);
    }

    private com.google.common.base.Optional<Message> receiveAndPreserve(ReceiveMessageOptions options,
            boolean preserveMessages) {
        com.google.common.base.Optional<Message> message = com.google.common.base.Optional.fromNullable(messageApi
                .receive(options));
        if (message.isPresent() && !preserveMessages) {
            messageApi.delete(message.get().getReceiptHandle());
        }
        return message;
    }

    private List<Message> receiveAllAtRateAndPreserve(int numberOfMessages, ReceiveMessageOptions options,
            boolean preserveMessages) {
        List<Message> messages = receiveAllAtRate(messageApi, numberOfMessages, options).toImmutableList();
        if (!preserveMessages) {
            deleteAllAtRate(messages, numberOfMessages);
        }
        return messages;
    }

    // invoke the callback on each of the messages
    private void process(Iterable<Message> messages, SourceCallback callback) {
        for (Message msg : messages) {
            process(msg, callback);
        }
    }

    // invoke the callback on each of the messages
    private void process(Message msg, SourceCallback callback) {
        try {
            callback.process(msg.getBody(), createProperties(msg));
        } catch (Exception e) {
            throw propagate(e);
        }
    }

    private void deleteAllAtRate(List<Message> messages, int rate) {
        for (List<String> page : partition(Lists.transform(messages, toReceiptHandle()), rate))
            messageApi.delete(page);
    }

    /**
     * @param msg
     * @return
     */
    public static Map<String, Object> createProperties(Message msg) {
        Builder<String, Object> properties = ImmutableMap.<String, Object> builder();
        properties.putAll(msg.getAttributes());
        properties.put("sqs.message.id", msg.getId());
        properties.put("sqs.message.md5", msg.getMD5());
        properties.put("sqs.message.receipt.handle", msg.getReceiptHandle());
        return properties.build();
    }

    /**
     * Deletes the message identified by message object on the queue this object
     * represents.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:delete-message}
     *
     * @param receiptHandle
     *            Receipt handle of the message to be deleted
     */
    @Processor
    public void deleteMessage(@Optional @Default("#[header:inbound:sqs.message.receipt.handle]") String receiptHandle) {
        messageApi.delete(receiptHandle);
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accessKey, region, queue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SQSConnector that = SQSConnector.class.cast(obj);
        return equal(this.accessKey, that.accessKey)
                && equal(this.region, that.region)
                && equal(this.queue, that.queue);
    }

    @Override
    public String toString() {
        return toStringHelper(this).omitNullValues()
                .add("accessKey", accessKey)
                .add("region", region)
                .add("queue", queue)
                .toString();
    }
}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import org.mule.api.annotations.*;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataStaticKey;
import org.mule.api.annotations.param.Optional;
import org.mule.api.callback.SourceCallback;
import org.mule.modules.sqs.connection.strategy.SQSConnectionManagement;
import org.mule.modules.sqs.metadata.category.SendMessageCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Amazon Simple Queue Service (Amazon SQS) is a distributed queue messaging service introduced by Amazon.com in
 * April of 2006. It supports programmatic sending of messages via web service applications as a way to communicate
 * over the internet. The intent of SQS is to provide a highly scalable hosted message queue that resolves issues
 * arising from the common producer-consumer problem or connectivity between producer and consumer.
 * <p/>
 * This connector does not provide a method for creating a queue. The reason being that it will automatically
 * create it when its needed instead of having to manually specify so.
 *
 * @author MuleSoft, Inc.
 */
@Connector(name = "sqs", friendlyName = "Amazon SQS")
public class SQSConnector {

    private static Logger logger = LoggerFactory.getLogger(SQSConnector.class);

    @NotNull
    @ConnectionStrategy
    private SQSConnectionManagement connection;

    private AmazonSQSClient msgQueue;
    private AmazonSQSAsync msgQueueAsync;

    /**
     * Adds a permission to this message queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:add-permission}
     *
     * @param label     a name for this permission
     * @param accountId the AWS account ID for the account to share this queue with
     * @param action    a value to indicate how much to share (SendMessage, ReceiveMessage, ChangeMessageVisibility, DeleteMessage, GetQueueAttributes)
     * @param queueUrl  Permissions will be added to the queue represented by this URL.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void addPermission(String label, String accountId, String action, @Optional String queueUrl) throws AmazonServiceException {
        msgQueue.addPermission(new AddPermissionRequest(getConnection().getQueueUrl(queueUrl), label,
                toList(accountId), toList(action)));
    }

    /**
     * Creates a new queue, or returns the URL of an existing one.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:create-queue}
     *
     * @param queueName  The name for the queue to be created.
     * @param region     The region in which the queue to be created.
     * @param attributes A map of attributes with their corresponding values.
     *                   Valid Map Keys: Policy | VisibilityTimeout | MaximumMessageSize | MessageRetentionPeriod |
     *                   ApproximateNumberOfMessages | ApproximateNumberOfMessagesNotVisible | CreatedTimestamp |
     *                   LastModifiedTimestamp | QueueArn | ApproximateNumberOfMessagesDelayed | DelaySeconds |
     *                   ReceiveMessageWaitTimeSeconds | RedrivePolicy
     * @return CreateQueueResult object containing the URL of the created Amazon SQS queue.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public CreateQueueResult createQueue(String queueName, @Optional RegionEndpoint region,
                                         @Optional Map<String, String> attributes) throws AmazonServiceException {
        if (region != null) {
            msgQueue.setEndpoint(region.value());
        }
        return msgQueue.createQueue(new CreateQueueRequest(queueName).withAttributes(attributes));
    }

    /**
     * Deletes the message identified by message object on the queue this object represents.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:delete-message}
     *
     * @param receiptHandle Receipt handle of the message to be deleted
     * @param queueUrl      The URL of the queue to delete messages from
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void deleteMessage(@Default("#[header:inbound:sqs.message.receipt.handle]") String receiptHandle,
                              @Optional String queueUrl) throws AmazonServiceException {
        msgQueue.deleteMessage(new DeleteMessageRequest(getConnection().getQueueUrl(queueUrl), receiptHandle));
    }

    /**
     * Deletes up to ten messages from the specified queue. This is a batch version of DeleteMessage.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:delete-message-batch}
     *
     * @param receiptHandles A list of receipt handles for the messages to be deleted.
     * @param queueUrl       The URL of the queue to delete messages as a batch from.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void deleteMessageBatch(@Default("#[payload]") List<DeleteMessageBatchRequestEntry> receiptHandles,
                                   @Optional String queueUrl) throws AmazonServiceException {
        msgQueue.deleteMessageBatch(new DeleteMessageBatchRequest(getConnection().getQueueUrl(queueUrl), receiptHandles));
    }

    /**
     * Deletes the message queue represented by this object. Will delete non-empty queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:delete-queue}
     *
     * @param queueUrl The URL of the queue to delete.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void deleteQueue(@Optional String queueUrl) throws AmazonServiceException {
        msgQueue.deleteQueue(new DeleteQueueRequest(getConnection().getQueueUrl(queueUrl)));
    }

    /**
     * Gets queue attributes. This is provided to expose the underlying functionality.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:get-queue-attributes}
     *
     * @param attributeNames A list of attribute retrieve information for.
     * @param queueUrl       The URL of the Amazon SQS queue to take action on.
     * @return GetQueueAttributesResult object map containing attributes and their values
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public GetQueueAttributesResult getQueueAttributes(@Optional List<String> attributeNames, @Optional String queueUrl) throws AmazonServiceException {
        return msgQueue.getQueueAttributes(new GetQueueAttributesRequest(getConnection().getQueueUrl(queueUrl))
                .withAttributeNames(attributeNames));
    }

    /**
     * Returns the URL of an existing queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:get-queue-url}
     *
     * @param queueName              The name of the queue whose URL must be fetched.
     * @param queueOwnerAWSAccountId The AWS account ID of the account that created the queue.
     * @return GetQueueUrlResult object containing the generated queue service url
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public GetQueueUrlResult getQueueUrl(String queueName, @Optional String queueOwnerAWSAccountId) throws AmazonServiceException {
        return msgQueue.getQueueUrl(new GetQueueUrlRequest(queueName).withQueueOwnerAWSAccountId(queueOwnerAWSAccountId));
    }

    /**
     * Returns a list of your queues that have the RedrivePolicy queue attribute configured with a dead letter queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:list-dead-letter-source-queues}
     *
     * @param queueUrl The queue URL of a dead letter queue.
     * @return ListDeadLetterSourceQueuesResult object containing a list of source queue URLs that have the RedrivePolicy queue attribute configured with a dead letter queue.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public ListDeadLetterSourceQueuesResult listDeadLetterSourceQueues(@Optional String queueUrl) throws AmazonServiceException {
        return msgQueue.listDeadLetterSourceQueues(new ListDeadLetterSourceQueuesRequest(getConnection().getQueueUrl(queueUrl)));
    }

    /**
     * Returns a list of your queues. The maximum number of queues that can be returned is 1000.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:list-queues}
     *
     * @param queueNamePrefix A string to use for filtering the list results. Only those queues whose name begins
     *                        with the specified string are returned.
     * @return ListQueuesResult object containing list of queue URLs.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public ListQueuesResult listQueues(@Optional String queueNamePrefix) throws AmazonServiceException {
        return msgQueue.listQueues(new ListQueuesRequest(queueNamePrefix));
    }

    /**
     * Deletes the messages in a queue specified by the queue URL.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:purge-queue}
     *
     * @param queueUrl the queue URL where messages are to be fetched from.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void purgeQueue(@Optional String queueUrl) throws AmazonServiceException {
        msgQueue.purgeQueue(new PurgeQueueRequest(getConnection().getQueueUrl(queueUrl)));
    }

    /**
     * Attempts to receive messages from a queue. Every attribute of the incoming
     * messages will be added as inbound properties. Also the following properties
     * will also be added:
     * <p/>
     * sqs.message.id = containing the message identification
     * sqs.message.receipt.handle = containing the message identification
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:receive-messages}
     *
     * @param callback          Callback to call when new messages are available.
     * @param visibilityTimeout the duration (in seconds) the retrieved messages are hidden from
     *                          subsequent calls to retrieve.
     * @param preserveMessages  Flag that indicates if you want to preserve the messages
     *                          in the queue. False by default, so the messages are
     *                          going to be deleted.
     * @param numberOfMessages  the number of messages to be retrieved on each call (10 messages max).
     *                          By default, 1 message will be retrieved.
     * @param queueUrl          the queue URL where messages are to be fetched from.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Source
    public void receiveMessages(SourceCallback callback,
                                @Default("30") Integer visibilityTimeout,
                                @Default("false") Boolean preserveMessages,
                                @Default("1") Integer numberOfMessages,
                                @Optional String queueUrl) throws AmazonServiceException {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withAttributeNames("All").withMessageAttributeNames("All");
        receiveMessageRequest.setQueueUrl(getConnection().getQueueUrl(queueUrl));

        if (visibilityTimeout != null) {
            receiveMessageRequest.setVisibilityTimeout(visibilityTimeout);
        }
        receiveMessageRequest.setMaxNumberOfMessages(numberOfMessages);

        while (!Thread.currentThread().isInterrupted()) {
            Future<ReceiveMessageResult> futureMessages = msgQueueAsync.receiveMessageAsync(receiveMessageRequest);
            try {
                List<Message> receivedMessages = futureMessages.get().getMessages();
                for (Message m : receivedMessages) {
                    try {
                        callback.process(m.getBody(), createProperties(m));
                    } catch (Exception e) {
                        // If an exception is thrown here, we cannot communicate
                        // with the Mule flow, so there is nothing we can do to
                        // handle it.
                        futureMessages.cancel(true);
                        return;
                    }
                    if (!preserveMessages) {
                        msgQueueAsync.deleteMessageAsync(new DeleteMessageRequest(getConnection().getQueueUrl(queueUrl), m.getReceiptHandle()));
                    }
                }
            } catch (InterruptedException e) {
                futureMessages.cancel(true);
                return;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Removes a permission from this message queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:remove-permission}
     *
     * @param label    a name for the permission to be removed
     * @param queueUrl Permissions will be deleted from the queue represented by this URL.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void removePermission(String label, @Optional String queueUrl) throws AmazonServiceException {
        msgQueue.removePermission(new RemovePermissionRequest(getConnection().getQueueUrl(queueUrl), label));
    }

    /**
     * Sends a message to a specified queue. The message must be between 1 and 256K bytes long.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:send-message}
     *
     * @param message           the message body to send.
     * @param delaySeconds      The number of seconds (0 to 900 - 15 minutes) to delay a specific message.
     * @param queueUrl          the queue where the message is to be sent.
     * @param messageAttributes a map of typed key-value pairs to be sent as message attributes. A value,
     *                          key and data type must be specified for each entry.
     * @return The queue response of the sent message.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    @MetaDataScope(SendMessageCategory.class)
    public SendMessageResult sendMessage(String message,
                                         @Optional Integer delaySeconds,
                                         @MetaDataStaticKey(type = "SendMessage")
                                         @Default("#[payload]") Map<String, Object> messageAttributes,
                                         @Optional String queueUrl) {
        Map<String, MessageAttributeValue> attributes = new HashMap<String, MessageAttributeValue>();
        if (messageAttributes instanceof Map && !messageAttributes.isEmpty()) {
            for (Map.Entry<String, Object> entry : messageAttributes.entrySet()) {
                attributes.put(entry.getKey(), (MessageAttributeValue) entry.getValue());
            }
        }
        return msgQueue.sendMessage(new SendMessageRequest(getConnection().getQueueUrl(queueUrl), message)
                .withDelaySeconds(delaySeconds).withMessageAttributes(attributes));
    }

    /**
     * Delivers up to ten messages to the specified queue. This is a batch version of SendMessage
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:send-message-batch}
     *
     * @param queueUrl the queue where the message is to be sent.
     * @param messages A list of SendMessageBatchRequestEntry items.
     * @return SendMessageBatchResult list items.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public SendMessageBatchResult sendMessageBatch(@Default("#[payload]") List<SendMessageBatchRequestEntry> messages, @Optional String queueUrl) throws AmazonServiceException {
        return msgQueue.sendMessageBatch(getConnection().getQueueUrl(queueUrl), messages);
    }

    /**
     * Sets a queue attribute. This is provided to expose the underlying functionality, although
     * the only attribute at this time is visibility timeout.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:set-queue-attribute}
     *
     * @param attribute name of the attribute being set
     * @param value     the value being set for this attribute
     * @param queueUrl  The URL of the queue.
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public void setQueueAttribute(String attribute, String value, @Optional String queueUrl)
            throws AmazonServiceException {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(attribute, value);
        msgQueue.setQueueAttributes(new SetQueueAttributesRequest(getConnection().getQueueUrl(queueUrl), attributes));
    }

    /**
     * Gets an approximate number of visible messages for a queue.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-sqs.xml.sample sqs:get-approximate-number-of-messages}
     *
     * @param queueUrl The URL of the queue
     * @return the approximate number of messages in the queue
     * @throws AmazonClientException  If any internal errors are encountered inside the client while
     *                                attempting to make the request or handle the response.  For example
     *                                if a network connection is not available.
     * @throws AmazonServiceException If an error response is returned by AmazonSQS indicating
     *                                either a problem with the data in the request, or a server side issue.
     */
    @Processor
    public int getApproximateNumberOfMessages(@Optional String queueUrl) throws AmazonServiceException {
        return Integer.parseInt(msgQueue.getQueueAttributes(
                new GetQueueAttributesRequest(getConnection().getQueueUrl(queueUrl)).withAttributeNames(
                        "ApproximateNumberOfMessages")).getAttributes().get("ApproximateNumberOfMessages"));
    }

    /**
     * @param msg Message to extract properties from.
     * @return map with properties
     */
    private Map<String, Object> createProperties(Message msg) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.putAll(msg.getAttributes());
        properties.putAll(msg.getMessageAttributes());
        properties.put("sqs.message.id", msg.getMessageId());
        properties.put("sqs.message.receipt.handle", msg.getReceiptHandle());
        return properties;
    }

    private List<String> toList(String element) {
        List<String> list = new ArrayList<String>();
        list.add(element);
        return list;
    }

    public SQSConnectionManagement getConnection() {
        return connection;
    }

    public void setConnection(SQSConnectionManagement connection) {
        this.connection = connection;
        this.msgQueue = connection.getMsgQueue();
        this.msgQueueAsync = connection.getMsgQueueAsync();
    }

}

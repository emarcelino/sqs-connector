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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.jclouds.sqs.options.ReceiveMessageOptions.Builder.attribute;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import junit.framework.TestCase;

import org.jclouds.crypto.CryptoStreams;
import org.jclouds.sqs.domain.BatchResult;
import org.jclouds.sqs.domain.Message;
import org.jclouds.sqs.features.MessageApi;
import org.jclouds.sqs.features.PermissionApi;
import org.jclouds.sqs.features.QueueApi;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.api.callback.SourceCallback;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.hash.HashCodes;

/**
 * Verifies that methods responsible for managing reference state and operations
 * operate as expected.
 *
 *
 * @author Adrian Cole
 *
 */

public class SQSConnectorTest extends TestCase {

    URI queue = URI.create("https://foo");
    String queueName = "queue";

    /**
     * The connect method has a number of responsibilities including setting
     * references of the remote connection and also creating a queue or reusing
     * one.
     *
     * If the queue exists via the {#link {@link QueueApi#get(String)} call, we
     * shouldn't attempt to create one.
     */
    public void testConnectSetsConnectionReferencesAndReusesQueue() throws ConnectionException {

        SQSConnection.Builder connectionBuilder = createMock(SQSConnection.Builder.class);
        SQSConnector connector = new SQSConnector(connectionBuilder);
        connector.setAccessKey("accessKey");
        connector.setSecretKey("secretKey");

        expect(connectionBuilder.credentials("accessKey", "secretKey")).andReturn(connectionBuilder);
        expect(connectionBuilder.region((String) null)).andReturn(connectionBuilder);

        SQSConnection connection = createMock(SQSConnection.class);
        QueueApi queueApi = createMock(QueueApi.class);
        MessageApi messageApi = createMock(MessageApi.class);
        PermissionApi permissionApi = createMock(PermissionApi.class);

        expect(connectionBuilder.build()).andReturn(connection);
        expect(connection.queueApi()).andReturn(queueApi);

        expect(queueApi.get(queueName)).andReturn(queue);

        expect(connection.messageApiForQueue(queue)).andReturn(messageApi);

        replay(connectionBuilder, connection, queueApi, messageApi, permissionApi);

        connector.connect(queueName);

        // ensure references are set
        assertEquals(connector.connection, connection);
        assertEquals(connector.queueApi, queueApi);
        assertEquals(connector.messageApi, messageApi);
        assertEquals(connector.queue, queue);

        verify(connectionBuilder, connection, queueApi, messageApi, permissionApi);

    }

    /**
     * If the queue is null via the {#link {@link QueueApi#get(String)} call, we
     * should attempt to create one.
     */
    public void testConnectSetsConnectionReferencesAndCreatesQueue() throws ConnectionException {

        SQSConnection.Builder connectionBuilder = createMock(SQSConnection.Builder.class);
        SQSConnector c = new SQSConnector(connectionBuilder);
        c.setAccessKey("accessKey");
        c.setSecretKey("secretKey");

        expect(connectionBuilder.credentials("accessKey", "secretKey")).andReturn(connectionBuilder);
        expect(connectionBuilder.region((String) null)).andReturn(connectionBuilder);

        SQSConnection connection = createMock(SQSConnection.class);
        QueueApi queueApi = createMock(QueueApi.class);
        MessageApi messageApi = createMock(MessageApi.class);

        expect(connectionBuilder.build()).andReturn(connection);
        expect(connection.queueApi()).andReturn(queueApi);

        expect(queueApi.get(queueName)).andReturn(null);
        expect(queueApi.create(queueName)).andReturn(queue);

        expect(connection.messageApiForQueue(queue)).andReturn(messageApi);

        replay(connectionBuilder, connection, queueApi, messageApi);

        c.connect(queueName);

        // ensure references are set
        assertEquals(c.connection, connection);
        assertEquals(c.queueApi, queueApi);
        assertEquals(c.messageApi, messageApi);
        assertEquals(c.queue, queue);

        verify(connectionBuilder, connection, queueApi, messageApi);

    }

    /**
     * disconnecting should close the sqs connection prior to clearing transient
     * references
     */
    public void testDisconnectClosesConnectionAndClearsReferences() {

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));

        SQSConnection connection = c.connection = createMock(SQSConnection.class);
        QueueApi queueApi = c.queueApi = createMock(QueueApi.class);
        MessageApi messageApi = c.messageApi = createMock(MessageApi.class);
        c.queue = queue;

        c.connection.close();
        expectLastCall();

        replay(c.connectionBuilder, connection, queueApi, messageApi);

        c.disconnect();

        // ensure references are unset
        assertEquals(c.connection, null);
        assertEquals(c.queueApi, null);
        assertEquals(c.messageApi, null);
        assertEquals(c.queue, null);

        verify(c.connectionBuilder, connection, queueApi, messageApi);

    }

    /**
     * since {@link SQSConnector#disconnect} and
     * {@link SQSConnector#deleteQueue()} clear references needed for other
     * operations, we have to check to ensure these are set in order to
     * determine if the connector is valid.
     */
    public void testIsConnectedWhenConnectionAndQueueRefsSetAndConnectionValid() {

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));

        c.connection = createMock(SQSConnection.class);
        c.queue = queue;

        expect(c.connection.valid()).andReturn(true);

        replay(c.connectionBuilder, c.connection);

        assertTrue(c.isConnected());

        verify(c.connectionBuilder, c.connection);

    }

    public void testIsntConnectedWhenConnectionOrQueueRefsAreUnset() {

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));

        c.queue = queue;

        assertFalse(c.isConnected());

        c.queue = null;
        c.connection = createMock(SQSConnection.class);

        assertFalse(c.isConnected());

    }

    Message msg = Message.builder()
        .id("5fea7756-0ea4-451a-a703-a558b933e274")
        .receiptHandle("+eXJYhj5rDr9cAe/9BuheT5fysi9BoqtEZSkO7IazVbNHg60eCCINxLqaSVv2pFHrWeWNpZwbleSkWRbCtZaQGgpOx/3cWJZiNSG1KKlJX4IOwISFvb3FwByMx4w0lnINeXzcw2VcKQXNrCatO9gdIiVPvJC3SCKatYM/7YTidtjqc8igrtYW2E2mHlCy3NXPCeXxP4tSvyEwIxpDAmMT7IF0mWvTHS6+JBUtFUsrmi61oIHlESNrD1OjdB1QQw+kdvJ6VbsntbJNNYKw+YqdqWNpZkiGQ8y1z9OdHsr1+4=")
        .md5(HashCodes.fromBytes(CryptoStreams.hex("fafb00f5732ab283681e124bf8747ed1")))
        .body("This is a test message").addAttribute("SenderId", "195004372649")
        .addAttribute("SentTimestamp", "1238099229000")
        .addAttribute("ApproximateReceiveCount", "5")
        .addAttribute("ApproximateFirstReceiveTimestamp", "1250700979248").build();


    class ValidatingCallback extends InterruptCallback {
        @Override
        public Object process(Object payload, Map<String, Object> properties) throws Exception {
            // make sure that all attributes from the original message passed through
            assertTrue(Maps.difference(properties, msg.getAttributes()).entriesOnlyOnRight().size() == 0);
            assertEquals(msg.getId(), properties.get("sqs.message.id"));
            assertEquals(msg.getMD5(), properties.get("sqs.message.md5"));
            assertEquals(msg.getReceiptHandle(), properties.get("sqs.message.receipt.handle"));
            return super.process(payload, properties);
        }
    };

    @Test
    public void testReceiveMessageWithPreserveMessagesFlagTrue() {
        int visibilityTimeout = 0;

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));
        c.messageApi = createMock(MessageApi.class);
        final AtomicReference<String> handle = new AtomicReference<String>();

        SourceCallback callback = new ValidatingCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception {
                handle.set(properties.get("sqs.message.receipt.handle").toString());
                return super.process(payload, properties);
            }
        };

        expect(c.messageApi.receive(attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(msg);

        // note there should be no delete call, as we've passed the preserve flag as true

        replay(c.connectionBuilder, c.messageApi);

        c.receiveMessages(callback, visibilityTimeout, true, 1000L, 1);

        assertEquals(messages.get(0).getReceiptHandle(), handle.get());

        verify(c.connectionBuilder, c.messageApi);

    }


    @Test
    public void testReceiveMessageWithPreserveMessagesFlagFalseDeletesMessage() {
        int visibilityTimeout = 0;

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));
        c.messageApi = createMock(MessageApi.class);
        final AtomicReference<String> handle = new AtomicReference<String>();

        SourceCallback callback = new ValidatingCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception {
                handle.set(properties.get("sqs.message.receipt.handle").toString());
                return super.process(payload, properties);
            }
        };

        expect(c.messageApi.receive(attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(msg);

        c.messageApi.delete(msg.getReceiptHandle());
        expectLastCall();

        replay(c.connectionBuilder, c.messageApi);

        c.receiveMessages(callback, visibilityTimeout, false, 1000L, 1);

        assertEquals(messages.get(0).getReceiptHandle(), handle.get());

        verify(c.connectionBuilder, c.messageApi);

    }

    List<Message> messages = ImmutableList.of(msg);

    @Test
    public void testReceiveMessagesWithPreserveMessagesFlagTrue() {
        int visibilityTimeout = 0;

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));
        c.messageApi = createMock(MessageApi.class);
        final AtomicReference<String> handle = new AtomicReference<String>();

        SourceCallback callback = new ValidatingCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception {
                handle.set(properties.get("sqs.message.receipt.handle").toString());
                return super.process(payload, properties);
            }
        };

        expect(c.messageApi.receive(2, attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(
                FluentIterable.from(messages));
        // checks again to ensure queue is empty
        expect(c.messageApi.receive(2, attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(
                FluentIterable.from(ImmutableList.<Message> of()));

        // note there should be no delete call, as we've passed the preserve
        // flag as true

        replay(c.connectionBuilder, c.messageApi);

        c.receiveMessages(callback, visibilityTimeout, true, 1000L, 2);

        assertEquals(messages.get(0).getReceiptHandle(), handle.get());

        verify(c.connectionBuilder, c.messageApi);

    }

    @Test
    public void testReceiveMessagesWithPreserveMessagesFlagFalseDeletesMessages() {
        int visibilityTimeout = 0;

        SQSConnector c = new SQSConnector(createMock(SQSConnection.Builder.class));
        c.messageApi = createMock(MessageApi.class);
        final AtomicReference<String> handle = new AtomicReference<String>();

        SourceCallback callback = new ValidatingCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception {
                handle.set(properties.get("sqs.message.receipt.handle").toString());
                return super.process(payload, properties);
            }
        };

        expect(c.messageApi.receive(2, attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(
                FluentIterable.from(messages));
        // checks again to ensure queue is empty
        expect(c.messageApi.receive(2, attribute("All").visibilityTimeout(visibilityTimeout))).andReturn(
                FluentIterable.from(ImmutableList.<Message> of()));

        expect(c.messageApi.delete(ImmutableList.of(messages.get(0).getReceiptHandle()))).andReturn(
                BatchResult.<String> builder().build());

        replay(c.connectionBuilder, c.messageApi);

        c.receiveMessages(callback, visibilityTimeout, false, 1000L, 2);

        assertEquals(messages.get(0).getReceiptHandle(), handle.get());

        verify(c.connectionBuilder, c.messageApi);

    }
}

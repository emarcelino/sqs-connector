/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mule.modules.sqs.connection.strategy.SQSConnectionManagement;
import org.mule.modules.sqs.model.ChangeMessageVisibilityBatchRequestEntry;
import org.mule.modules.sqs.model.DeleteMessageBatchRequestEntry;
import org.mule.modules.sqs.util.SQSModelFactory;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class SQSConnectorTest {

    private SQSConnector connector;

    @Mock
    private AmazonSQSClient msgQueue;

    @Mock
    private AmazonSQSAsync msgQueueAsync;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.connector = new SQSConnector();

        SQSConnectionManagement connectionManagement = new SQSConnectionManagement();
        connectionManagement.setMsgQueue(msgQueue);
        connectionManagement.setMsgQueueAsync(msgQueueAsync);
        connectionManagement.setUrl(ConnectorTestUtils.generateRandomShortString());

        this.connector.setConnection(connectionManagement);
    }

    @Test
    public void testAddPermission() {
        try {
            doNothing().when(msgQueue).addPermission(Mockito.mock(AddPermissionRequest.class));
            connector.addPermission("foo", Arrays.asList("05678940987"), Arrays.asList("SendMessage"), null);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testChangeMessageVisibilityBatch() {
        try {
            ChangeMessageVisibilityBatchResult messageVisibilityBatchResult = Mockito.mock(ChangeMessageVisibilityBatchResult.class);
            when(msgQueue.changeMessageVisibilityBatch(any(ChangeMessageVisibilityBatchRequest.class))).thenReturn(messageVisibilityBatchResult);
            org.mule.modules.sqs.model.ChangeMessageVisibilityBatchResult operationResult = connector.changeMessageVisibilityBatch(Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "handle")), "url");
            assertEquals(operationResult, SQSModelFactory.getChangeMessageVisibilityBatchResult(messageVisibilityBatchResult.getSuccessful(), messageVisibilityBatchResult.getFailed()));
            assertTrue(operationResult.getFailed() != null);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testChangeMessageVisibility() {
        try {
            doNothing().when(msgQueue).changeMessageVisibility(any(ChangeMessageVisibilityRequest.class));
            connector.changeMessageVisibility("receiptHandle", 60, "queueUrl");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testCreateQueue() {
        try {
            CreateQueueResult createQueueResult = Mockito.mock(CreateQueueResult.class);
            when(msgQueue.createQueue(any(CreateQueueRequest.class))).thenReturn(createQueueResult);

            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("VisibilityTimeout", "50");

            assertEquals(connector.createQueue("foo", RegionEndpoint.SAEAST1, attributes),
                    new org.mule.modules.sqs.model.CreateQueueResult().withQueueUrl(createQueueResult.getQueueUrl()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testDeleteMessageBatch() {
        try {
            DeleteMessageBatchResult deleteMessageBatchResult = Mockito.mock(DeleteMessageBatchResult.class);
            when(msgQueue.deleteMessageBatch(any(DeleteMessageBatchRequest.class))).thenReturn(deleteMessageBatchResult);
            assertEquals(connector.deleteMessageBatch(Arrays.asList(new DeleteMessageBatchRequestEntry("id", "handle")), "url"),
                    SQSModelFactory.getDeleteMessageBatchResult(deleteMessageBatchResult.getSuccessful(), deleteMessageBatchResult.getFailed()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }


    @Test
    public void testDeleteMessage() {
        try {
            doNothing().when(msgQueue).deleteMessage(any(DeleteMessageRequest.class));
            connector.deleteMessage("http://sqs.us-east-1.amazonaws.com/", "handle");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testDeleteQueue() {
        try {
            doNothing().when(msgQueue).deleteQueue(Mockito.mock(DeleteQueueRequest.class));
            connector.deleteQueue("http://sqs.us-east-1.amazonaws.com/");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testGetQueueAttributes() {
        try {
            GetQueueAttributesResult queueAttributesResult = Mockito.mock(GetQueueAttributesResult.class);
            when(msgQueue.getQueueAttributes(any(GetQueueAttributesRequest.class))).thenReturn(queueAttributesResult);
            assertEquals(connector.getQueueAttributes(Arrays.asList("VisibilityTimeout"), "http://sqs.us-east-1.amazonaws.com/"),
                    new org.mule.modules.sqs.model.GetQueueAttributesResult().withAttributes(queueAttributesResult.getAttributes()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }


    @Test
    public void testGetQueueUrl() {
        try {
            GetQueueUrlResult queueUrlResult = Mockito.mock(GetQueueUrlResult.class);
            when(msgQueue.getQueueUrl(any(GetQueueUrlRequest.class))).thenReturn(queueUrlResult);
            assertEquals(connector.getQueueUrl("testQueue", "accountId"),
                    new org.mule.modules.sqs.model.GetQueueUrlResult().withQueueUrl(queueUrlResult.getQueueUrl()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testListDeadLetterSourceQueues() {
        try {
            ListDeadLetterSourceQueuesResult listDeadLetterSourceQueuesResult = Mockito.mock(ListDeadLetterSourceQueuesResult.class);
            when(msgQueue.listDeadLetterSourceQueues(any(ListDeadLetterSourceQueuesRequest.class))).thenReturn(listDeadLetterSourceQueuesResult);
            assertEquals(connector.listDeadLetterSourceQueues("queueUrl"),
                    new org.mule.modules.sqs.model.ListDeadLetterSourceQueuesResult().withQueueUrls(listDeadLetterSourceQueuesResult.getQueueUrls()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testListQueues() {
        try {
            ListQueuesResult listQueuesResult = Mockito.mock(ListQueuesResult.class);
            when(msgQueue.listQueues(any(ListQueuesRequest.class))).thenReturn(listQueuesResult);
            assertEquals(connector.listQueues("queuePrefix"),
                    new org.mule.modules.sqs.model.ListQueuesResult().withQueueUrls(listQueuesResult.getQueueUrls()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testPurgeQueue() {
        try {
            doNothing().when(msgQueue).purgeQueue(any(PurgeQueueRequest.class));
            connector.purgeQueue("queueUrl");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testRemovePermission() {
        try {
            doNothing().when(msgQueue).removePermission(any(RemovePermissionRequest.class));
            connector.removePermission("label", "queueUrl");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testSendMessageBatch() {
        try {
            SendMessageBatchResult messageBatchResult = Mockito.mock(SendMessageBatchResult.class);
            when(msgQueue.sendMessageBatch(anyString(), anyList())).thenReturn(messageBatchResult);
            List<org.mule.modules.sqs.model.SendMessageBatchRequestEntry> messages = new ArrayList<org.mule.modules.sqs.model.SendMessageBatchRequestEntry>(0);
            assertEquals(connector.sendMessageBatch(messages, "queueUrl"),
                    SQSModelFactory.getSendMessageBatchResult(messageBatchResult.getSuccessful(), messageBatchResult.getFailed()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testSendMessage() {
        try {
            SendMessageResult sendMessageResult = Mockito.mock(SendMessageResult.class);
            when(msgQueue.sendMessage(any(SendMessageRequest.class))).thenReturn(sendMessageResult);

            assertEquals(connector.sendMessage("message", 0, new HashMap<String, Object>(0), "queueUrl"),
                    SQSModelFactory.getSendMessageResult(sendMessageResult.getMessageId(), sendMessageResult.getMD5OfMessageBody(), sendMessageResult.getMD5OfMessageAttributes()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testSetQueueAttribute() {
        try {
            doNothing().when(msgQueue).setQueueAttributes(any(SetQueueAttributesRequest.class));
            connector.setQueueAttributes(new HashMap<String, String>(0), "queueUrl");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}


/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation;

import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.QueueDeletedRecentlyException;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.MessagingException;
import org.mule.modules.tests.ConnectorTestCase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SqsTestParent extends ConnectorTestCase {

    private static final int MAX_RETRIES = 10;
    private static final long RECREATE_QUEUE_DELAY = 61 * 1000;
    private static final long RETRY_DELAY = RECREATE_QUEUE_DELAY / MAX_RETRIES;
    private static final String CONNECTION_ERROR_MSG = "Failed to connect after "
            + MAX_RETRIES + " retries";
    @Rule
    // Increase timeout to allow some retrying
    public Timeout globalTimeout = new Timeout(240 * 1000);

    protected void setQueueAttribute(String attribute, String value)
            throws Exception {
        upsertOnTestRunMessage("attribute", attribute);
        upsertOnTestRunMessage("value", value);
        runFlowAndGetPayload("set-queue-attribute");
    }

    protected void deleteQueue() throws Exception {
        runFlowAndGetPayload("delete-queue");
    }

    protected Integer getApproximateNumberOfMessages() throws Exception {
        return (Integer) runFlowAndGetPayload("get-approximate-number-of-messages");
    }

    protected SendMessageResult sendMessage(String message, String queueUrl, Map<String, MessageAttributeValue> messageAttributes) throws Exception {
        upsertOnTestRunMessage("messageAttributes", messageAttributes);
        upsertOnTestRunMessage("message", message);
        upsertOnTestRunMessage("queueUrl", queueUrl);
        return runFlowAndGetPayload("send-message-with-attributes");
    }

    protected SendMessageResult sendMessage(String message) throws Exception {
        upsertOnTestRunMessage("message", message);
        return runFlowAndGetPayload("send-message");
    }

    private void sleepOnException(MessagingException e,
                                  Class<? extends Exception> expectedClass)
            throws InterruptedException, MessagingException {
        if (e.causedBy(expectedClass)) {
            Thread.sleep(RETRY_DELAY);
        } else {
            throw e;
        }
    }

    @Override
    protected <T> T runFlowAndGetPayload(String flowName) throws Exception {
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                Thread.sleep(5000); // compensate for reduced latency in US
                return super.runFlowAndGetPayload(flowName);
            } catch (MessagingException e) {
                sleepOnException(e, QueueDeletedRecentlyException.class);
            }
        }
        throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, null,
                CONNECTION_ERROR_MSG);
    }

    @Override
    protected <T> T runFlowAndGetPayload(String flowName, String beanId)
            throws Exception {
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                Thread.sleep(5000); // compensate for reduced latency in US
                return super.runFlowAndGetPayload(flowName, beanId);
            } catch (MessagingException e) {
                sleepOnException(e, QueueDeletedRecentlyException.class);
            }
        }
        throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, null,
                CONNECTION_ERROR_MSG);
    }

    protected String md5(String string) throws NoSuchAlgorithmException {
        return new String(Hex.encode(MessageDigest.getInstance("MD5").digest(string.getBytes())));
    }

}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SendMessageTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("sendMessageTestData");
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSendMessage() {
        try {
            String message = UUID.randomUUID().toString();
            assertEquals(0, (int) getApproximateNumberOfMessages());
            SendMessageResult result = sendMessage(message);
            assertEquals(1, (int) getApproximateNumberOfMessages());
            assertEquals(md5(message), result.getMD5OfMessageBody());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSendMessageWithAttributes() {
        try {
            Map<String, MessageAttributeValue> attributes = new HashMap<String, MessageAttributeValue>();
            attributes.put("key", new MessageAttributeValue().withDataType("String").withStringValue("value"));
            SendMessageResult result = sendMessage((String) getTestRunMessageValue("message"),
                    ((GetQueueUrlResult) runFlowAndGetPayload("get-queue-url")).getQueueUrl(), attributes);
            assertEquals(32, result.getMD5OfMessageAttributes().length());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

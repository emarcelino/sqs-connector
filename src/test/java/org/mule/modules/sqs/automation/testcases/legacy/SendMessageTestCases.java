/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SendMessageTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("sendMessageTestData");
    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testSendMessage() {
        try {
            assertEquals(0, (int) getApproximateNumberOfMessages());
            SendMessageResult result = runFlowAndGetPayload("send-message");
            assertEquals(md5((String) getTestRunMessageValue("message")), result.getMD5OfMessageBody());

            Thread.sleep(5000);

            assertEquals(1, (int) getApproximateNumberOfMessages());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testSendMessageWithAttributes() {
        try {
            Map<String, MessageAttributeValue> messageAttributes = new HashMap<String, MessageAttributeValue>();
            messageAttributes.put("AccountId", new MessageAttributeValue().withDataType("String.AccountId").withStringValue("000123456"));
            messageAttributes.put("NumberId", new MessageAttributeValue().withDataType("Number").withStringValue("230.000000000000000001"));

            upsertOnTestRunMessage("messageAttributes", messageAttributes);

            SendMessageResult result = runFlowAndGetPayload("send-message");

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

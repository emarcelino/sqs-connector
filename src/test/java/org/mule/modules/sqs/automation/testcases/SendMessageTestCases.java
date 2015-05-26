/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testcases;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.sqs.model.MessageAttributeValue;
import org.mule.modules.sqs.model.SendMessageResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SendMessageTestCases extends SQSFunctionalTestParent {

    String queueUrl;
    String message = ConnectorTestUtils.generateRandomShortString();
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSendMessage() {
        try {
            assertEquals(0, getConnector().getApproximateNumberOfMessages(queueUrl));
            SendMessageResult result = getConnector().sendMessage(message, 0, null, queueUrl);
            assertEquals(md5(message), result.getMD5OfMessageBody());

            Thread.sleep(10000);

            assertEquals(1, getConnector().getApproximateNumberOfMessages(queueUrl));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSendMessageWithAttributes() {
        try {
            Map<String, Object> messageAttributes = new HashMap<String, Object>();
            messageAttributes.put("AccountId", new MessageAttributeValue().withDataType("String.AccountId").withStringValue("000123456"));
            messageAttributes.put("NumberId", new MessageAttributeValue().withDataType("Number").withStringValue("230.000000000000000001"));

            SendMessageResult result = getConnector().sendMessage(message, 0, messageAttributes, queueUrl);

            assertEquals(md5(message), result.getMD5OfMessageBody());
            assertEquals(32, result.getMD5OfMessageAttributes().length());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (StringUtils.isNotBlank(queueUrl)) {
            getConnector().deleteQueue(queueUrl);
        }
    }
}

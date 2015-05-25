/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.sqs.model.GetQueueAttributesResult;
import org.mule.modules.sqs.model.GetQueueUrlResult;
import org.mule.modules.sqs.model.ListDeadLetterSourceQueuesResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ListDeadLetterSourceQueuesTestCases extends SqsTestParent {

    String queueUrl;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("listDeadLetterSourceQueuesTestData");

        CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
        queueUrl = createQueueResult.getQueueUrl();
        upsertOnTestRunMessage("queueUrl", queueUrl);
        Map<String, String> attributes = ((GetQueueAttributesResult) runFlowAndGetPayload("get-queue-attributes")).getAttributes();
        String redrivePolicy = String.format("{\"maxReceiveCount\":\"%s\", \"deadLetterTargetArn\":\"%s\"}",
                getTestRunMessageValue("maxReceiveCount").toString(), attributes.get("QueueArn"));

        attributes = new HashMap<String, String>();
        attributes.put("RedrivePolicy", redrivePolicy);
        upsertOnTestRunMessage("attributes", attributes);
        removeFromTestRunMessage("queueUrl");
        runFlowAndGetPayload("set-queue-attribute");
    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testListDeadLetterSourceQueues() {
        try {
            upsertOnTestRunMessage("queueUrl", queueUrl);
            ListDeadLetterSourceQueuesResult result = runFlowAndGetPayload("list-dead-letter-source-queues");
            assertTrue(result.getQueueUrls() != null);
            upsertOnTestRunMessage("queueName", getTestRunMessageValue("expectedQueueName").toString());
            String expectedQueueUrl = ((GetQueueUrlResult) runFlowAndGetPayload("get-queue-url")).getQueueUrl();
            assertEquals(expectedQueueUrl, result.getQueueUrls().get(0));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        // Delete the DeadLetterQueue
        deleteQueue();
        // Delete the Connection Queue
        removeFromTestRunMessage("queueUrl");
        deleteQueue();
    }
}
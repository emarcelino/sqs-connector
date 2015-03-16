/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ListDeadLetterSourceQueuesTestCases extends SqsTestParent {

    String queueUrl;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("listDeadLetterSourceQueuesTestData");

        CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
        queueUrl =createQueueResult.getQueueUrl();
        upsertOnTestRunMessage("queueUrl", queueUrl);
        Map<String, String> attributes = ((GetQueueAttributesResult) runFlowAndGetPayload("get-queue-attributes")).getAttributes();
        String redrivePolicy = String.format("{\"maxReceiveCount\":\"%s\", \"deadLetterTargetArn\":\"%s\"}",
                getTestRunMessageValue("maxReceiveCount").toString(), attributes.get("QueueArn"));
        upsertOnTestRunMessage("attribute", "RedrivePolicy");
        upsertOnTestRunMessage("value", redrivePolicy);
        runFlowAndGetPayload("set-queue-attribute");
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testListDeadLetterSourceQueues() {
        try {
            ListDeadLetterSourceQueuesResult result = runFlowAndGetPayload("list-dead-letter-source-queues");
            assertTrue(result.getQueueUrls() != null);
            upsertOnTestRunMessage("queueName", getTestRunMessageValue("expectedQueueName").toString());
            String expectedQueueUrl = ((GetQueueUrlResult)runFlowAndGetPayload("get-queue-url")).getQueueUrl();
            assertEquals(expectedQueueUrl, result.getQueueUrls().get(0));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception{
        // Delete the DeadLetterQueue
        deleteQueue();
        // Delete the Connection Queue
        removeFromTestRunMessage("queueUrl");
        deleteQueue();
    }
}

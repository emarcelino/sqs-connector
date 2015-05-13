/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ListDeadLetterSourceQueuesTestCases extends SQSFunctionalTestParent {

    String queueUrl;
    final String queueName = "SQSConnectorDeadLetterQueue";
    Map<String, String> attributes;

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(queueName, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
        attributes = getConnector().getQueueAttributes(Arrays.asList("QueueArn"), queueUrl).getAttributes();
        String redrivePolicy = String.format("{\"maxReceiveCount\":\"%s\", \"deadLetterTargetArn\":\"%s\"}",
                5, attributes.get("QueueArn"));

        attributes = new HashMap<String, String>();
        attributes.put("RedrivePolicy", redrivePolicy);
        getConnector().setQueueAttributes(attributes, null);
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testListDeadLetterSourceQueues() {
        try {
            ListDeadLetterSourceQueuesResult result = getConnector().listDeadLetterSourceQueues(queueUrl);
            assertTrue(result.getQueueUrls() != null);
            String expectedQueueUrl = getConnector().getQueueUrl(queueName, null).getQueueUrl();
            assertEquals(expectedQueueUrl, result.getQueueUrls().get(0));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        // Delete the DeadLetterQueue
        getConnector().deleteQueue(queueUrl);
        // Delete the source queue
        getConnector().deleteQueue(null);
    }
}

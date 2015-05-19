/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;
import org.apache.commons.lang.StringUtils;
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
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ListDeadLetterSourceQueuesTestCases extends SQSFunctionalTestParent {

    String deadLetterQueueUrl;
    String sourceQueueUrl;
    Map<String, String> attributes;

    @Before
    public void setUp() throws Exception {
        CreateQueueResult deadLetterQueue = getConnector().createQueue("SQSConnectorDeadLetterQueue", RegionEndpoint.USEAST1, null);
        deadLetterQueueUrl = deadLetterQueue.getQueueUrl();

        CreateQueueResult mySourceQueue = getConnector().createQueue("mySourceQueue", RegionEndpoint.USEAST1, null);
        sourceQueueUrl = mySourceQueue.getQueueUrl();
        attributes = getConnector().getQueueAttributes(Arrays.asList("QueueArn"), deadLetterQueueUrl).getAttributes();
        String redrivePolicy = String.format("{\"maxReceiveCount\":\"%s\", \"deadLetterTargetArn\":\"%s\"}",
                5, attributes.get("QueueArn"));

        attributes = new HashMap<String, String>();
        attributes.put("RedrivePolicy", redrivePolicy);
        getConnector().setQueueAttributes(attributes, sourceQueueUrl);
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testListDeadLetterSourceQueues() {
        try {
            ListDeadLetterSourceQueuesResult result = getConnector().listDeadLetterSourceQueues(deadLetterQueueUrl);
            List<String> queueUrls = result.getQueueUrls();
            assertTrue(queueUrls != null);
            if (queueUrls.size() > 0) {
                assertEquals(sourceQueueUrl, result.getQueueUrls().get(0));
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        // Delete the DeadLetterQueue
        if (StringUtils.isNotBlank(deadLetterQueueUrl)) {
            getConnector().deleteQueue(deadLetterQueueUrl);
        }
        if (StringUtils.isNotBlank(sourceQueueUrl)) {
            getConnector().deleteQueue(sourceQueueUrl);
        }
    }
}

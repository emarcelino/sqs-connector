/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ListQueuesTestCases extends SqsTestParent {

    Map<String, String> queueUrls = new HashMap<String, String>(0);

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("listQueuesTestData");
        List<String> queues = getTestRunMessageValue("queueNames");
        String queuePrefix = getTestRunMessageValue("queuePrefix");
        for (String queue : queues) {
            upsertOnTestRunMessage("queueName", queue);
            CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
            if (queue.startsWith(queuePrefix)) {
                queueUrls.put(queue, createQueueResult.getQueueUrl());
            }
        }
    }

    @Category({LegacyRegressionTests.class})
    @Test
    public void testListQueues() {
        try {
            ListQueuesResult queuesResult = runFlowAndGetPayload("list-queues");
            for (String queueUrl : queueUrls.values()) {
                assertTrue((queuesResult.getQueueUrls()).contains(queueUrl));
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        // Delete the connection queue
        deleteQueue();

        if (!queueUrls.isEmpty()) {
            for (String url : queueUrls.values()) {
                upsertOnTestRunMessage("queueUrl", url);
                deleteQueue();
            }
            removeFromTestRunMessage("queueUrl");
        }
    }


}

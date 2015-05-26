/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.sqs.model.ListQueuesResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ListQueuesTestCases extends SQSFunctionalTestParent {

    Map<String, String> queueUrls = new HashMap<String, String>(0);
    String queuePrefix = "T";

    @Before
    public void setUp() throws Exception {
        List<String> queues = Arrays.asList("testSQSConnectorQueue", "TestSQSDevQueue", "SQSConnectorTestQueue");
        for (String queue : queues) {
            CreateQueueResult createQueueResult = getConnector().createQueue(queue, RegionEndpoint.USEAST1, null);
            queueUrls.put(queue, createQueueResult.getQueueUrl());
        }
    }

    @Category({RegressionTests.class})
    @Test
    public void testListQueues() {
        try {
            ListQueuesResult queuesResult = getConnector().listQueues(queuePrefix);
            for (String queue : queueUrls.keySet()) {
                if (queue.startsWith(queuePrefix)) {
                    assertTrue((queuesResult.getQueueUrls()).contains(queueUrls.get(queue)));
                }
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!queueUrls.isEmpty()) {
            for (String url : queueUrls.values()) {
                getConnector().deleteQueue(url);
            }
        }
    }
}

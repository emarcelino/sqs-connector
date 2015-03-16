/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PurgeQueueTestCases extends SqsTestParent {

    String queueUrl;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("purgeQueueTestData");
        CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
        queueUrl = createQueueResult.getQueueUrl();
        upsertOnTestRunMessage("queueUrl", queueUrl);
        for (int i = 0; i <= 5; i++) {
            String message = UUID.randomUUID().toString();
            SendMessageResult result = sendMessage(message);
        }
        assertEquals(5, (int) getApproximateNumberOfMessages());

    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testPurgeQueue() {
        try {
            runFlowAndGetPayload("purge-queue");
            assertEquals(0, (int) getApproximateNumberOfMessages());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

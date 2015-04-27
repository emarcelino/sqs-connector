/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageBatchResultEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class SendMessageBatchTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("sendMessageBatchTestData");
    }

    @Category({RegressionTests.class})
    @Test
    public void testSendMessageBatch() {
        try {

            SendMessageBatchResult result = runFlowAndGetPayload("send-message-batch");
            List<SendMessageBatchResultEntry> successful = result.getSuccessful();
            assertTrue(!successful.isEmpty());

            assertEquals(((List) getTestRunMessageValue("messages")).size(), (int) getApproximateNumberOfMessages());

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }

}

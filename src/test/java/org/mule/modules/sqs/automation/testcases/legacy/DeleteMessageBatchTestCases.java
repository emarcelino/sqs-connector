/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResult;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResultEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.construct.Flow;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DeleteMessageBatchTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteMessageBatchTestData");
        runFlowAndGetPayload("send-message-batch");
    }

    @Category({LegacyRegressionTests.class})
    @Test
    public void testDeleteMessageBatch() {
        try {
            List<DeleteMessageBatchRequestEntry> receiptHandles = new ArrayList<DeleteMessageBatchRequestEntry>(0);
            List<SendMessageBatchRequestEntry> messages = getTestRunMessageValue("messages");

            assertEquals(messages.size(), (int) getApproximateNumberOfMessages());

            Flow flow = muleContext.getRegistry().get("receive-messages");
            flow.start();
            for (SendMessageBatchRequestEntry message : messages) {
                Map payload = (Map) muleContext.getClient().request("vm://receive", 5000).getPayload();
                receiptHandles.add(new DeleteMessageBatchRequestEntry((String) payload.get("messageId"), (String) payload.get("receiptHandle")));
            }
            flow.stop();

            upsertOnTestRunMessage("receiptHandles", receiptHandles);
            DeleteMessageBatchResult result = runFlowAndGetPayload("delete-message-batch");
            List<DeleteMessageBatchResultEntry> resultEntries = result.getSuccessful();
            assertTrue(!resultEntries.isEmpty());

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

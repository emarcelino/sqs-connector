/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageBatchResultEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SendMessageBatchTestCases extends SQSFunctionalTestParent {

    String queueUrl;

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class})
    @Test
    public void testSendMessageBatch() {
        try {
            List<SendMessageBatchRequestEntry> messages = new ArrayList<SendMessageBatchRequestEntry>();
            messages.add(new SendMessageBatchRequestEntry("id-1", ConnectorTestUtils.generateRandomShortString()));
            messages.add(new SendMessageBatchRequestEntry("id-2", ConnectorTestUtils.generateRandomShortString()));

            SendMessageBatchResult result = getConnector().sendMessageBatch(messages, queueUrl);
            List<SendMessageBatchResultEntry> successful = result.getSuccessful();
            assertTrue(!successful.isEmpty());
            assertEquals(messages.size(), getConnector().getApproximateNumberOfMessages(queueUrl));

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        getConnector().deleteQueue(queueUrl);
    }

}

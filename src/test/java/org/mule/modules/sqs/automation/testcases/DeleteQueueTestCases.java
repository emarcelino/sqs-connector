/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DeleteQueueTestCases extends SQSFunctionalTestParent {

    private boolean queueDeleted = false;
    private String queueUrl;
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setup() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testDeleteQueue() {

        try {
            assertEquals(0, getConnector().getApproximateNumberOfMessages(queueUrl));
            getConnector().deleteQueue(queueUrl);
            queueDeleted = true;
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

        try {
            getConnector().getApproximateNumberOfMessages(queueUrl);
        } catch (Exception e) {
            if (e.getCause() instanceof MessagingException) {
                MessagingException me = (MessagingException) e.getCause();
                if (me.getCause() instanceof AmazonServiceException) {
                    AmazonServiceException serviceException = (AmazonServiceException) me.getCause();
                    assertEquals("AWS.SimpleQueueService.NonExistentQueue", serviceException.getErrorCode());
                }
            } else {
                fail(ConnectorTestUtils.getStackTrace(e));
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!queueDeleted) {
            getConnector().deleteQueue(queueUrl);
        }
    }
}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetApproximateNumberOfMessagesTestCases extends SQSFunctionalTestParent {

    private final List<String> messages = Arrays.asList("Amazon SQS Test Message 1",
            "Amazon SQS Test Message 2", "Amazon SQS Test Message 3");

    private String queueUrl;
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class})
    @Test
    public void testGetApproximateNumberOfMessages() {
        try {
            assertEquals(0, getConnector().getApproximateNumberOfMessages(queueUrl));

            for (String message : messages) {
                getConnector().sendMessage(message, 0, null, queueUrl);
                Thread.sleep(5000);
            }
            assertEquals(messages.size(), getConnector().getApproximateNumberOfMessages(queueUrl));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (StringUtils.isNotBlank(queueUrl)) {
            getConnector().deleteQueue(queueUrl);
        }
    }
}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.AmazonServiceException;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RemovePermissionTestCases extends SQSFunctionalTestParent {

    String queueUrl;
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setup() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
        List<String> accountIds = Arrays.asList("055970264539");
        List<String> actions = Arrays.asList("SendMessage", "ReceiveMessage", "DeleteMessage");
        try {
            getConnector().addPermission("fooPermission", accountIds, actions, queueUrl);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testRemovePermission() {
        try {
            getConnector().removePermission("fooPermission", queueUrl);
            Thread.sleep(5000);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
        // Retry to remove the above permission throws exception.
        try {
            getConnector().removePermission("fooPermission", queueUrl);
        } catch (Exception e) {
            if (e instanceof AmazonServiceException) {
                AmazonServiceException serviceException = (AmazonServiceException) e;
                assertEquals("InvalidParameterValue", serviceException.getErrorCode());
                assertEquals(String.format("Value %s for parameter Label is invalid. Reason: can't find label on existing policy.",
                        "fooPermission"), serviceException.getErrorMessage());
            } else {
                fail(ConnectorTestUtils.getStackTrace(e));
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        if (StringUtils.isNotBlank(queueUrl)) {
            getConnector().deleteQueue(queueUrl);
        }
    }
}

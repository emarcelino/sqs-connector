/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.apache.commons.lang.StringUtils;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddPermissionTestCases extends SQSFunctionalTestParent {

    String queueUrl = null;

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testAddPermission() {
        // Using valid principal id to add permissions.
        List<String> accountIds = Arrays.asList("055970264539");
        List<String> actions = Arrays.asList("SendMessage", "ReceiveMessage");
        try {
            getConnector().addPermission("fooPermission", accountIds, actions, queueUrl);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

        // Using invalid principal id to add permissions.
        accountIds = Arrays.asList(ConnectorTestUtils.generateRandomShortString());
        try {
            getConnector().addPermission("fooPermission", accountIds, actions, queueUrl);
        } catch (Exception e) {
            if (e.getCause() instanceof MessagingException) {
                MessagingException me = (MessagingException) e.getCause();
                if (me.getCause() instanceof AmazonServiceException) {
                    AmazonServiceException serviceException = (AmazonServiceException) me.getCause();
                    assertEquals("InvalidParameterValue", serviceException.getErrorCode());
                    assertEquals(String.format("Value %s for parameter PrincipalId is invalid. Reason: Unable to verify.", accountIds), serviceException.getErrorMessage());
                }
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

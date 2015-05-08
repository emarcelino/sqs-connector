/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddPermissionTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("addPermissionTestData");
    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testAddPermission() {
        // Using valid principal id to add permissions.
        try {
            upsertOnTestRunMessage("queueUrl", ((GetQueueUrlResult) runFlowAndGetPayload("get-queue-url")).getQueueUrl());
            runFlowAndGetPayload("add-permission");
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

        // Using invalid principal id to add permissions.
        List<String> accountIds = Arrays.asList(ConnectorTestUtils.generateRandomShortString());
        try {
            upsertOnTestRunMessage("accountIds", accountIds);
            runFlowAndGetPayload("add-permission");
        } catch (Exception e) {
            if (e.getCause() instanceof AmazonServiceException) {
                AmazonServiceException serviceException = (AmazonServiceException) e.getCause();
                assertEquals("InvalidParameterValue", serviceException.getErrorCode());
            } else {
                fail(ConnectorTestUtils.getStackTrace(e));
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.AmazonServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.sqs.model.GetQueueUrlResult;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RemovePermissionTestCases extends SqsTestParent {

    @Before
    public void setup() throws Exception {
        initializeTestRunMessage("removePermissionTestData");
        upsertOnTestRunMessage("queueUrl", ((GetQueueUrlResult) runFlowAndGetPayload("get-queue-url")).getQueueUrl());
        runFlowAndGetPayload("add-permission");
    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testRemovePermission() {
        try {
            runFlowAndGetPayload("remove-permission");
            Thread.sleep(5000);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
        // Retry to remove the above permission throws exception.
        try {
            runFlowAndGetPayload("remove-permission");
        } catch (Exception e) {
            if (e.getCause() instanceof AmazonServiceException) {
                AmazonServiceException serviceException = (AmazonServiceException) e.getCause();
                assertEquals("InvalidParameterValue", serviceException.getErrorCode());
                assertEquals(String.format("Value %s for parameter Label is invalid. Reason: can't find label on existing policy.",
                        getTestRunMessageValue("label")), serviceException.getErrorMessage());
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

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.construct.Flow;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ChangeMessageVisibilityTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("changeMessageVisibilityTestData");
        runFlowAndGetPayload("send-message");
    }

    @Category({LegacyRegressionTests.class})
    @Test
    public void testChangeMessageVisibility() {
        try {
            Flow flow = muleContext.getRegistry().get("receive-messages");
            flow.start();
            Map payload = (Map) muleContext.getClient().request("vm://receive", 5000).getPayload();
            assertEquals(getTestRunMessageValue("message"), payload.get("messageBody"));
            upsertOnTestRunMessage("receiptHandle", payload.get("receiptHandle"));
            flow.stop();

            runFlowAndGetPayload("change-message-visibility");

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

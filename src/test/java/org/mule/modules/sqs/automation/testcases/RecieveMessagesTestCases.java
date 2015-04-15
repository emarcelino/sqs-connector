/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.construct.Flow;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RecieveMessagesTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("receiveMessageTestData");
        runFlowAndGetPayload("send-message");
    }


    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testRecieveMessages() {
        try {
            Flow flow = muleContext.getRegistry().get("receive-message");
            flow.start();
            Object payload = muleContext.getClient().request("vm://receive", 5000).getPayload();
            assertEquals(getTestRunMessageValue("message"), payload);
            Thread.sleep(5000);
            flow.stop();
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }

}

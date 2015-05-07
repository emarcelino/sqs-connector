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
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetApproximateNumberOfMessagesTestCases extends SqsTestParent {

    @Before
    public void setup() {
        initializeTestRunMessage("getApproximateNumberOfMessagesTestData");
    }


    @Category({LegacyRegressionTests.class})
    @Test
    public void testGetApproximateNumberOfMessages() {
        try {
            assertEquals(0, (int) getApproximateNumberOfMessages());
            final List<String> messages = (List<String>) getTestRunMessageValue("messages");

            for (String message : messages) {
                sendMessage(message);
            }

            assertEquals(messages.size(), (int) getApproximateNumberOfMessages());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }


}

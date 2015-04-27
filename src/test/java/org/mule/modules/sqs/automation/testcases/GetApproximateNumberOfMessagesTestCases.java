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
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetApproximateNumberOfMessagesTestCases extends SqsTestParent {

    @Before
    public void setup() {
        initializeTestRunMessage("getApproximateNumberOfMessagesTestData");
    }


    @Category({RegressionTests.class})
    @Test
    public void testGetApproximateNumberOfMessages() throws Exception {
        assertEquals(0, (int) getApproximateNumberOfMessages());

        for (String message : (List<String>) getTestRunMessageValue("messages")) {
            sendMessage(message);
        }

        assertEquals(4, (int) getApproximateNumberOfMessages());
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }


}

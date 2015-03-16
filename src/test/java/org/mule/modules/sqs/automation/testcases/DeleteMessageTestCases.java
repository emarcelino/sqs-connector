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
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;

public class DeleteMessageTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("receiveMessageTestData");
    }


    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testDeleteMessage() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

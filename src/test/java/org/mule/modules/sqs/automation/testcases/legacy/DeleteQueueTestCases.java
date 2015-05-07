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
import org.mule.api.MessagingException;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

public class DeleteQueueTestCases extends SqsTestParent {
    private boolean queueDeleted = false;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteQueueTestData");
    }

    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    @Test
    public void testDeleteQueue() {
        try {
            assertEquals(0, (int) getApproximateNumberOfMessages());
            deleteQueue();
            queueDeleted = true;
            try {
                getApproximateNumberOfMessages();
            } catch (MessagingException e) {
                assertTrue(e.getSummaryMessage().contains("NonExistentQueue"));
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!queueDeleted) {
            deleteQueue();
        }
    }

}

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
import org.mule.api.MessagingException;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteQueueTestCases extends SqsTestParent {
    private boolean queueDeleted = false;

    @Category({RegressionTests.class, SmokeTests.class})
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
        }catch (Exception e){
            
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!queueDeleted) {
            deleteQueue();
        }
    }

}

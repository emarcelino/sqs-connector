/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetQueueAttributeTestCases extends SqsTestParent {

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSetGetQueueAttribute() throws Exception {
        setQueueAttribute("MaximumMessageSize", "1111");
        //assertEquals("1111", getQueueAttribute("MaximumMessageSize"));
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSetInvalidQueueAttribute() throws Exception {
        try {
            setQueueAttribute("MaximumSizeMessage", "1111");
        } catch (MessagingException e) {
            System.out.println(e.getSummaryMessage());
            assertTrue(e.getSummaryMessage().contains("InvalidAttributeName"));
        }
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void setInvalidQueueAttributeValue() throws Exception {
        try {
            // value must be between 1024 and 262144
            setQueueAttribute("MaximumMessageSize", "1000");
        } catch (MessagingException e) {
            System.out.println(e.getSummaryMessage());
            assertTrue(e.getSummaryMessage().contains("InvalidAttributeValue"));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

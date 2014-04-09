/**
 * Mule Amazon SQS Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package org.mule.modules.automation.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetQueueAttributesTestCases
    extends SqsTestParent
{
    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }

    @Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testGetDefaultQueueAttributes()
        throws Exception
    {
    	assertEquals("0", getQueueAttribute("DelaySeconds"));
    	assertEquals("262144", getQueueAttribute("MaximumMessageSize"));
    	assertEquals("345600", getQueueAttribute("MessageRetentionPeriod"));
    }
}

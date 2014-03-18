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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetQueueAttributesTestCases
    extends SqsTestParent
{


    @Before
    public void setup() {
        //TODO: Add setup required to run test or remove method
        initializeTestRunMessage("getQueueAttributesTestData");
    }

    @After
    public void tearDown() {
        //TODO: Add code to reset sandbox state to the one before the test was run or remove
    }

    @Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testGetQueueAttributes()
        throws Exception
    {
        Object result = runFlowAndGetPayload("get-queue-attributes");
        throw new RuntimeException("NOT IMPLEMENTED METHOD");
    }

}

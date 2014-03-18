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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetUrlTestCases
    extends SqsTestParent
{


    @Before
    public void setup() {
        initializeTestRunMessage("testGetUrlTestData");
    }

    @After
    public void tearDown() {
    }

    @Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testGetUrl()
        throws Exception
    {
        String result = runFlowAndGetPayload("get-url");
        assertNotNull(result);
        
        String queueName = "testQueue";
        String urlFormat = "https://sqs.us-east-1.amazonaws.com/.*/"+queueName;
        
        assertTrue(result.matches(urlFormat));
    }

}

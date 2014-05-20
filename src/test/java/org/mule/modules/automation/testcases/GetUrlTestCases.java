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

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.automation.RegressionTests;
import org.mule.modules.automation.SmokeTests;

public class GetUrlTestCases
    extends SqsTestParent
{
	private String queueName;
	
    @Before
    public void setup() {
        Properties props = getBeanFromContext("testAccountCredentials");
        queueName = props.getProperty("sqs.queueName");
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
        String result = getQueueUrl();
        assertNotNull(result);
        
        String urlFormat = "http[s]?://sqs.us-west-2.amazonaws.com/.*/"+queueName;
        System.out.println(result);
        
        assertTrue(result.matches(urlFormat));
    }

}

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

public class GetApproximateNumberOfMessagesTestCases
    extends SqsTestParent
{
    @Before
    public void setup() {
    }

    @After
    public void tearDown() throws Exception {
    	
    	deleteQueue();
    }

    @Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testGetApproximateNumberOfMessages()
        throws Exception
    {
    	initializeTestRunMessage("testGetApproximateNumberOfMessagesTestData");

    	Integer numberOfMessages = 0;
    	Integer result = null; 
    	try{
    		result = runFlowAndGetPayload("get-approximate-number-of-messages");

    	} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}

    	assertEquals(numberOfMessages, result);

    	sendMessage("firstMessage");
    	sendMessage("secondMessage");
        sendMessage("thirdMessage");
        sendMessage("fourthMessage");
    	numberOfMessages = 4;

        try{
        	result = runFlowAndGetPayload("get-approximate-number-of-messages");
        	
    	} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
        
        assertEquals(numberOfMessages, result );
    }

}

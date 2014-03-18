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

public class SendMessageTestCases
    extends SqsTestParent
{


    @Before
    public void setup() {
    }

    @After
    public void tearDown() throws Exception {
    	//FIXME configure console so the messages last only a couple of seconds
    	//in that case, deleting the queue will not be needed
    	deleteQueue();
    	//Amazon forces you to wait 60secs before re-creating the queue
    	Thread.sleep(59000);
    }


	@Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testSendMessage()
        throws Exception
    {
		initializeTestRunMessage("testSendMessageFromPayloadTestData");
		
        runFlowAndGetPayload("send-message-from-payload");
        
        
        
        
    }

}

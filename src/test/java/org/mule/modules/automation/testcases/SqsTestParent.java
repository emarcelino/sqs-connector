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

import org.mule.modules.tests.ConnectorTestCase;

public class SqsTestParent
    extends ConnectorTestCase
{
    protected void deleteQueue() throws Exception {
		
    	initializeTestRunMessage("testDeleteQueueTestData");
    	runFlowAndGetPayload("delete-queue");
	}
    
    protected void sendMessage(String message) throws Exception {
    	
    	initializeTestRunMessage("testSendMessageTestData");
    	upsertOnTestRunMessage("message", message);
    	runFlowAndGetPayload("send-message-custom-message");
	}
    
    
    
}

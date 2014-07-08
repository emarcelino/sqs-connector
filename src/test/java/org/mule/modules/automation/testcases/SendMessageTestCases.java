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

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.bouncycastle.util.encoders.Hex;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.automation.RegressionTests;
import org.mule.modules.automation.SmokeTests;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SendMessageTestCases
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
    public void testSendMessage()
        throws Exception
    {
		String message = UUID.randomUUID().toString();
		assertEquals(0, (int) getApproximateNumberOfMessages());
		SendMessageResult result = sendMessage(message);
		assertEquals(1, (int) getApproximateNumberOfMessages());
		assertEquals(md5(message), result.getMD5OfMessageBody());
    }
	
	private String md5(String string) throws NoSuchAlgorithmException {
		return new String(Hex.encode(MessageDigest.getInstance("MD5").digest(string.getBytes())));
	}
    @Category({ RegressionTests.class, SmokeTests.class })
	@Test
	public void testSendMessageCustomUrl() throws Exception {
		String message = UUID.randomUUID().toString();
		assertEquals(0, (int) getApproximateNumberOfMessages());
		SendMessageResult result = sendMessage(message, getQueueUrl());
		assertEquals(1, (int) getApproximateNumberOfMessages(getQueueUrl()));
		assertEquals(md5(message), result.getMD5OfMessageBody());
	}

    @Category({ RegressionTests.class, SmokeTests.class })
    @Test
    public void testSendMessageWithAttributes() throws Exception {
        Map<String, MessageAttributeValue> attributes = new HashMap<String, MessageAttributeValue>();
        attributes.put("key", new MessageAttributeValue().withDataType("String").withStringValue("value"));
        SendMessageResult result = sendMessage("message", getQueueUrl(), attributes);
        assertEquals(32, result.getMD5OfMessageAttributes().length());
    }

}

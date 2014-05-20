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
import org.mule.api.MessagingException;
import org.mule.modules.automation.RegressionTests;
import org.mule.modules.automation.SmokeTests;

public class AddPermissionTestCases extends SqsTestParent {

	@After
	public void tearDown() throws Exception {
		deleteQueue();
		deleteAltQueue();
	}

	@Category({ RegressionTests.class, SmokeTests.class })
	@Test
	public void testAddPermission() throws Exception {
		assertEquals(0, (int) getApproximateNumberOfMessages().intValue());
		try {
			sendMessageFromAlt("sup", getQueueUrl());
			fail("An access denied error should have been thrown");
		} catch (MessagingException e) {
			assertTrue(e.getSummaryMessage().contains("AccessDenied"));
		}
		addPermission("fooPermission", getAltPrincipalId(), "SendMessage", getQueueUrl());
		sendMessageFromAlt("sup", getQueueUrl());
		assertEquals(1, (int) getApproximateNumberOfMessages());
	}
	

}

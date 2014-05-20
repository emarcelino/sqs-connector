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
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.automation.RegressionTests;
import org.mule.modules.automation.SmokeTests;

public class RemovePermissionTestCases extends SqsTestParent {

	String accountId;
	
	@Before
	public void setup() {
	}

	@After
	public void tearDown() throws Exception {
		deleteQueue();
		deleteAltQueue();
	}

	@Category({ RegressionTests.class, SmokeTests.class })
	@Test
	public void testRemovePermission() throws Exception {
		String queueUrl = getQueueUrl();
		assertEquals(0, (int) getApproximateNumberOfMessages());
		addPermission("fooPermission", getAltPrincipalId(), "SendMessage", queueUrl);
		sendMessageFromAlt("sup", queueUrl);
		assertEquals(1, (int) getApproximateNumberOfMessages());
		removePermission("fooPermission", queueUrl);
		try {
			sendMessageFromAlt("hi", queueUrl);
			fail("An access denied error should have been thrown");
		} catch (MessagingException e) {
			assertTrue(e.getSummaryMessage().contains("AccessDenied"));
		}
	}

	private void removePermission(String label, String queueUrl) throws Exception {
		initializeTestRunMessage("label", label);
		upsertOnTestRunMessage("queueUrl", queueUrl);
		runFlowAndGetPayload("remove-permission");
	}

}

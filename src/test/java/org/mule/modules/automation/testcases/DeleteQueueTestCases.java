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

public class DeleteQueueTestCases extends SqsTestParent {
	private boolean queueDeleted = false;

	@Before
	public void setup() {
		initializeTestRunMessage("testDeleteQueueTestData");
	}

	@After
	public void tearDown() throws Exception {
		if (!queueDeleted) {
			deleteQueue();
		}
	}

	@Category({ RegressionTests.class, SmokeTests.class })
	@Test
	public void testDeleteQueue() throws Exception {
		assertEquals(0, getApproximateNumberOfMessages());
		deleteQueue();
		queueDeleted = true;
		try {
			getApproximateNumberOfMessages();
		} catch (MessagingException e) {
			assertTrue(e.getSummaryMessage().contains("NonExistentQueue"));
		}
	}

}

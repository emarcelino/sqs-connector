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
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;

public class SetQueueAttributeTestCases extends SqsTestParent {

	@After
	public void tearDown() throws Exception {
		deleteQueue();
	}

	@Category({ RegressionTests.class, SmokeTests.class })
	@Test
	public void testSetGetQueueAttribute() throws Exception {
		setQueueAttribute("MaximumMessageSize", "1111");
		assertEquals("1111", getQueueAttribute("MaximumMessageSize"));
	}

	@Test
	public void testSetInvalidQueueAttribute() throws Exception {
		try {
			setQueueAttribute("MaximumSizeMessage", "1111");
		} catch (MessagingException e) {
			System.out.println(e.getSummaryMessage());
			assertTrue(e.getSummaryMessage().contains("InvalidAttributeName"));
		}
	}

	@Test
	public void setInvalidQueueAttributeValue() throws Exception {
		try {
			// value must be between 1024 and 262144
			setQueueAttribute("MaximumMessageSize", "1000");
		} catch (MessagingException e) {
			System.out.println(e.getSummaryMessage());
			assertTrue(e.getSummaryMessage().contains("InvalidAttributeValue"));
		}
	}

}

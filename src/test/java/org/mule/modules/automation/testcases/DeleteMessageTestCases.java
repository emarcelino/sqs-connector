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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteMessageTestCases extends SqsTestParent {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		deleteQueue();
	}

	@Category({ RegressionTests.class, SmokeTests.class })
	@Test
	@Ignore
	public void testDeleteMessage() throws Exception {

	}

}

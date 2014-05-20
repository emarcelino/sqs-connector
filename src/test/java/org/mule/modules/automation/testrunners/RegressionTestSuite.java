/**
 * Mule Amazon SQS Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package org.mule.modules.automation.testrunners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.mule.modules.automation.testcases.AddPermissionTestCases;
import org.mule.modules.automation.testcases.DeleteMessageTestCases;
import org.mule.modules.automation.testcases.DeleteQueueTestCases;
import org.mule.modules.automation.testcases.GetApproximateNumberOfMessagesTestCases;
import org.mule.modules.automation.testcases.GetQueueAttributesTestCases;
import org.mule.modules.automation.testcases.GetUrlTestCases;
import org.mule.modules.automation.RegressionTests;
import org.mule.modules.automation.testcases.RemovePermissionTestCases;
import org.mule.modules.automation.testcases.SendMessageTestCases;
import org.mule.modules.automation.testcases.SetQueueAttributeTestCases;

@RunWith(Categories.class)
@Categories.IncludeCategory(RegressionTests.class)
@org.junit.runners.Suite.SuiteClasses({
    AddPermissionTestCases.class,
    SendMessageTestCases.class,
    GetUrlTestCases.class,
    DeleteMessageTestCases.class,
    DeleteQueueTestCases.class,
    GetQueueAttributesTestCases.class,
    SetQueueAttributeTestCases.class,
    RemovePermissionTestCases.class,
    GetApproximateNumberOfMessagesTestCases.class
})
public class RegressionTestSuite {


}

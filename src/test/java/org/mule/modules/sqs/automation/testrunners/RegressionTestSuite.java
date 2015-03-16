/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testrunners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.testcases.*;

@RunWith(Categories.class)
@Categories.IncludeCategory(RegressionTests.class)
@org.junit.runners.Suite.SuiteClasses({
        AddPermissionTestCases.class,
        SendMessageTestCases.class,
        GetQueueUrlTestCases.class,
        DeleteMessageTestCases.class,
        DeleteQueueTestCases.class,
        GetQueueAttributesTestCases.class,
        SetQueueAttributeTestCases.class,
        RemovePermissionTestCases.class,
        GetApproximateNumberOfMessagesTestCases.class
})
public class RegressionTestSuite {


}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testrunners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.testcases.legacy.*;

@RunWith(Categories.class)
@Categories.IncludeCategory(LegacySmokeTests.class)
@org.junit.runners.Suite.SuiteClasses({
        SQSConnectorMetaDataTestCases.class,
        SendMessageTestCases.class,
        GetQueueUrlTestCases.class,
        DeleteMessageTestCases.class,
        DeleteQueueTestCases.class,
        GetQueueAttributesTestCases.class,
        SetQueueAttributeTestCases.class,
        AddPermissionTestCases.class,
        RemovePermissionTestCases.class,
        ListDeadLetterSourceQueuesTestCases.class,
        RecieveMessagesTestCases.class
})
public class LegacySmokeTestSuite {
}

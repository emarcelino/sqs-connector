/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testrunners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.mule.modules.sqs.SQSConnector;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.testcases.*;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;
import org.mule.tools.devkit.ctf.platform.PlatformManager;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTests.class)
@org.junit.runners.Suite.SuiteClasses({
        SendMessageTestCases.class,
        GetQueueUrlTestCases.class,
        DeleteQueueTestCases.class,
        GetQueueAttributesTestCases.class,
        SetQueueAttributesTestCases.class,
        AddPermissionTestCases.class,
        RemovePermissionTestCases.class,
        ListDeadLetterSourceQueuesTestCases.class,
        SQSConnectorMetaDataTestCases.class
})
public class SmokeTestSuite {

    @BeforeClass
    public static void initialiseSuite() throws Exception {
        ConnectorTestContext.initialize(SQSConnector.class);
    }

    @AfterClass
    public static void shutdownSuite() throws Exception {
        ConnectorTestContext<SQSConnector> context = ConnectorTestContext.getInstance(SQSConnector.class);
        PlatformManager platformManager = context.getPlatformManager();
        platformManager.shutdown();
    }
}

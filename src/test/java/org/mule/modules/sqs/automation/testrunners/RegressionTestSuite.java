/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testrunners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.sqs.SQSConnector;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.testcases.*;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

@RunWith(Categories.class)
@IncludeCategory(RegressionTests.class)
@SuiteClasses({
        AddPermissionTestCases.class,
        CreateQueueTestCases.class,
        DeleteQueueTestCases.class,
        GetApproximateNumberOfMessagesTestCases.class,
        GetQueueAttributesTestCases.class,
        GetQueueUrlTestCases.class,
        ListDeadLetterSourceQueuesTestCases.class,
        ListQueuesTestCases.class,
        PurgeQueueTestCases.class,
        RemovePermissionTestCases.class,
        SendMessageTestCases.class,
        SendMessageBatchTestCases.class,
        SetQueueAttributesTestCases.class,
        SQSConnectorMetaDataTestCases.class
})
public class RegressionTestSuite {

    @BeforeClass
    public static void initialiseSuite() throws Exception {
        ConnectorTestContext.initialize(SQSConnector.class);
    }

    @AfterClass
    public static void shutdownSuite() throws Exception {
        ConnectorTestContext.shutDown();
    }
}

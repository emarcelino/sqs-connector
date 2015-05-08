/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testcases.legacy;

import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

public class GetQueueUrlTestCases extends SqsTestParent {
    private String queueName;

    @Before
    public void setup() {
        initializeTestRunMessage("getQueueUrlTestData");
        queueName = getTestRunMessageValue("queueName");
    }

    @Category({LegacyRegressionTests.class})
    @Test
    public void testGetQueueUrl() {
        try {
            GetQueueUrlResult result = runFlowAndGetPayload("get-queue-url");

            assertNotNull(result);
            String region = RegionEndpoint.valueOf((String) getTestRunMessageValue("region")).value();

            String urlFormat = "http[s]?://" + region + "/.*/" + queueName;
            assertTrue(result.getQueueUrl().matches(urlFormat));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }

}

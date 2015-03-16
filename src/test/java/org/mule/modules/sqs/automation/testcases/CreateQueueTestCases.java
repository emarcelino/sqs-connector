/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class CreateQueueTestCases extends SqsTestParent {

    String queueUrl = null;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("createQueueTestData");
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testCreateQueue() {
        try {

            CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
            queueUrl = createQueueResult.getQueueUrl();
            assertNotNull(queueUrl);

            GetQueueUrlResult getQueueUrlResult = runFlowAndGetPayload("get-queue-url");
            assertEquals(queueUrl, getQueueUrlResult.getQueueUrl());

            Map<String, String> expectedAttributes = getTestRunMessageValue("attributes");
            upsertOnTestRunMessage("attributeNames", new ArrayList<String>(expectedAttributes.keySet()));
            upsertOnTestRunMessage("queueUrl", queueUrl);

            GetQueueAttributesResult getQueueAttributesResult = runFlowAndGetPayload("get-queue-attributes");
            Map<String, String> actualAttributes = getQueueAttributesResult.getAttributes();
            assertEquals(expectedAttributes.get("VisibilityTimeout"), actualAttributes.get("VisibilityTimeout"));
            assertEquals(expectedAttributes.get("DelaySeconds"), actualAttributes.get("DelaySeconds"));
            assertEquals(expectedAttributes.get("MessageRetentionPeriod"), actualAttributes.get("MessageRetentionPeriod"));

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (queueUrl != null) {
            deleteQueue();
        }
    }
}

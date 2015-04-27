/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SqsTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetQueueAttributesTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getQueueAttributesTestData");

        CreateQueueResult createQueueResult = runFlowAndGetPayload("create-queue");
        String queueUrl = createQueueResult.getQueueUrl();
        upsertOnTestRunMessage("queueUrl", queueUrl);
    }

    @Category({RegressionTests.class})
    @Test
    public void testGetDefaultQueueAttributes() {
        try {
            Map<String, String> attributes = ((GetQueueAttributesResult) runFlowAndGetPayload("get-queue-attributes")).getAttributes();
            assertTrue(CollectionUtils.containsAny((List) getTestRunMessageValue("attributeNames"), attributes.keySet()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }

}

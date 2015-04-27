/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SetQueueAttributeTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("setQueueAttributeTestData");
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSetGetQueueAttribute() throws Exception {
        runFlowAndGetPayload("set-queue-attribute");

        GetQueueUrlResult result = runFlowAndGetPayload("get-queue-url");
        upsertOnTestRunMessage("queueUrl", result.getQueueUrl());
        Map<String, String> actualAttributes = getTestRunMessageValue("attributes");

        upsertOnTestRunMessage("attributeNames", new ArrayList<String>(actualAttributes.keySet()));

        Map<String, String> expectedAttributes = ((GetQueueAttributesResult) runFlowAndGetPayload("get-queue-attributes")).getAttributes();
        assertEquals(expectedAttributes.keySet(), actualAttributes.keySet());

        for (Map.Entry<String, String> entry : expectedAttributes.entrySet()) {
            assertEquals(actualAttributes.get(entry.getKey()), entry.getValue());
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteQueue();
    }
}

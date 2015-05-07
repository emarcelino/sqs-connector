/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CreateQueueTestCases extends SQSFunctionalTestParent {

    String testQueueUrl = null;
    final String testQueue = "testConnectorQueue";

    @Category({RegressionTests.class})
    @Test
    public void testCreateQueue() {
        try {
            Map<String, String> queueAttributes = new HashMap<String, String>();
            queueAttributes.put("VisibilityTimeout", "50");
            queueAttributes.put("DelaySeconds", "100");
            queueAttributes.put("MessageRetentionPeriod", "120");


            CreateQueueResult createQueueResult = getConnector().createQueue(testQueue, RegionEndpoint.USWEST1, queueAttributes);
            testQueueUrl = createQueueResult.getQueueUrl();

            GetQueueUrlResult getQueueUrlResult = getConnector().getQueueUrl(testQueue, null);
            assertEquals(testQueueUrl, getQueueUrlResult.getQueueUrl());

            GetQueueAttributesResult getQueueAttributesResult = getConnector().getQueueAttributes(new ArrayList<String>(queueAttributes.keySet()), testQueueUrl);
            Map<String, String> actualAttributes = getQueueAttributesResult.getAttributes();
            assertEquals(queueAttributes.get("VisibilityTimeout"), actualAttributes.get("VisibilityTimeout"));
            assertEquals(queueAttributes.get("DelaySeconds"), actualAttributes.get("DelaySeconds"));
            assertEquals(queueAttributes.get("MessageRetentionPeriod"), actualAttributes.get("MessageRetentionPeriod"));

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (StringUtils.isNotBlank(testQueueUrl)) {
            getConnector().deleteQueue(testQueueUrl);
        }
    }
}

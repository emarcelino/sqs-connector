/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;


import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SetQueueAttributesTestCases extends SQSFunctionalTestParent {

    String queueUrl;

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testSetQueueAttributes() {
        try {
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("MaximumMessageSize", "1111");
            attributes.put("DelaySeconds", "10");
            getConnector().setQueueAttributes(attributes, queueUrl);

            GetQueueUrlResult result = getConnector().getQueueUrl(TEST_QUEUE_NAME, null);

            Map<String, String> expectedAttributes = getConnector().getQueueAttributes(new ArrayList<String>(attributes.keySet()), queueUrl).getAttributes();
            assertEquals(expectedAttributes.keySet(), attributes.keySet());

            for (Map.Entry<String, String> entry : expectedAttributes.entrySet()) {
                assertEquals(attributes.get(entry.getKey()), entry.getValue());
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        getConnector().deleteQueue(queueUrl);
    }
}

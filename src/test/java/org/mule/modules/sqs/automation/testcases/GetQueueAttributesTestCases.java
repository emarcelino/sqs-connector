/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.sqs.model.CreateQueueResult;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetQueueAttributesTestCases extends SQSFunctionalTestParent {

    String queueUrl;
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setUp() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testGetQueueAttributes() {
        final List<String> attributeList = Arrays.asList("VisibilityTimeout", "DelaySeconds", "MessageRetentionPeriod");
        try {
            Map<String, String> attributes = getConnector().getQueueAttributes(attributeList, queueUrl).getAttributes();
            assertTrue(CollectionUtils.containsAny((List) attributeList, attributes.keySet()));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        if (StringUtils.isNotBlank(queueUrl)) {
            getConnector().deleteQueue(queueUrl);
        }
    }

}

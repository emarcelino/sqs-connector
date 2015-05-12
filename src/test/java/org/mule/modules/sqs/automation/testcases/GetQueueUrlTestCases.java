/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;


import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

public class GetQueueUrlTestCases extends SQSFunctionalTestParent {

    private String queueUrl;
    final String TEST_QUEUE_NAME = ConnectorTestUtils.generateRandomShortString();

    @Before
    public void setup() throws Exception {
        CreateQueueResult createQueueResult = getConnector().createQueue(TEST_QUEUE_NAME, RegionEndpoint.USEAST1, null);
        queueUrl = createQueueResult.getQueueUrl();
    }


    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testGetQueueUrl() {
        try {
            GetQueueUrlResult result = getConnector().getQueueUrl(TEST_QUEUE_NAME, null);

            assertNotNull(result);
            String region = RegionEndpoint.valueOf(TEST_QUEUE_REGION).value();

            String urlFormat = "http[s]?://" + region + "/.*/" + TEST_QUEUE_NAME;
            assertTrue(result.getQueueUrl().matches(urlFormat));

            queueUrl = result.getQueueUrl();
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

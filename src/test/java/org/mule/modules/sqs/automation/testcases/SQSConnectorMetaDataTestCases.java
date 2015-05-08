/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.common.Result;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.modules.sqs.automation.RegressionTests;
import org.mule.modules.sqs.automation.SQSFunctionalTestParent;
import org.mule.modules.sqs.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class SQSConnectorMetaDataTestCases extends SQSFunctionalTestParent {

    @Test
    @Category({RegressionTests.class, SmokeTests.class})
    public void testSendMessageMetaData() {
        MetaDataKey sendMessageKey = null;
        try {

            Result<List<MetaDataKey>> metaDataKeysResult = getDispatcher().fetchMetaDataKeys();

            assertTrue(Result.Status.SUCCESS.equals(metaDataKeysResult.getStatus()));
            List<MetaDataKey> metaDataKeys = metaDataKeysResult.get();

            for (MetaDataKey key : metaDataKeys) {
                if (sendMessageKey == null && key.getId().equals("sendMessage")) {
                    sendMessageKey = key;
                }
            }

            assertNotNull(sendMessageKey);

            Result<MetaData> sendMessageKeyResult = getDispatcher().fetchMetaData(sendMessageKey);
            assertTrue(Result.Status.SUCCESS.equals(sendMessageKeyResult.getStatus()));

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }
}

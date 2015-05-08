/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation.testcases.legacy;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.common.Result;
import org.mule.common.metadata.*;
import org.mule.common.metadata.datatype.DataType;
import org.mule.modules.sqs.automation.LegacyRegressionTests;
import org.mule.modules.sqs.automation.LegacySmokeTests;
import org.mule.modules.sqs.automation.SqsTestParent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SQSConnectorMetaDataTestCases extends SqsTestParent {

    private ConnectorMetaDataEnabled connector;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getMetaDataKeysTestData");
        connector = muleContext.getRegistry().lookupObject("SQS");
        Thread.sleep(10000);
    }

    @Test
    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    public void testGetMetaDataKeysSuccess() {
        assertGetMetaDataKeysSuccess("SQS");
    }

    @Test
    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    public void testGetMetaDataKeysAmount() {
        List<MetaDataKey> retrievedMetadataKeys = getMetaDataKeyList("SQS");
        assertEquals(retrievedMetadataKeys.size(), Integer.parseInt((String) getTestRunMessageValue("expectedMetaDataKeysCount")));
    }

    @Test
    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    public void testGetMetaDataKeysContainsKeys() {
        assertMetaDataKeysContainsKeys("SQS", (List<HashMap<String, String>>) getTestRunMessageValue("expectedMetaDataKeys"));
    }

    @Test
    @Category({LegacyRegressionTests.class, LegacySmokeTests.class})
    public void testGetMetaData() {
        List<MetaDataKey> metaDataKeys = getMetaDataKeyList("SQS");
        Iterator<MetaDataKey> iterator = metaDataKeys.iterator();

        while (iterator.hasNext()) {
            MetaDataKey metaDataKey = iterator.next();
            Result<MetaData> metaData = connector.getMetaData(metaDataKey);
            assertTrue(Result.Status.SUCCESS.equals(metaData.getStatus()));
        }
        MetaDataModel payload = getInputMetaDataPayload("send-message-meta-data");
        ParameterizedMapMetaDataModel mapMetaDataModel = (DefaultParameterizedMapMetaDataModel) payload;
        MetaDataModel keyMetaDataModel = mapMetaDataModel.getKeyMetaDataModel();
        assertEquals(DataType.STRING, keyMetaDataModel.getDataType());
        MetaDataModel valueMetaDataModel = mapMetaDataModel.getValueMetaDataModel();
        assertEquals(DataType.POJO, valueMetaDataModel.getDataType());

        payload = getOutputMetaDataPayload("send-message-meta-data");
        PojoMetaDataModel pojoMetaDataModel = (DefaultPojoMetaDataModel) payload;
        List<MetaDataField> fields = pojoMetaDataModel.getFields();
        assertEquals(3, fields.size());

    }
}

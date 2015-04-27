/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.metadata.category;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.components.MetaDataCategory;
import org.mule.common.metadata.*;
import org.mule.common.metadata.builder.DefaultMetaDataBuilder;
import org.mule.common.metadata.builder.MetaDataBuilder;
import org.mule.common.metadata.datatype.DataType;
import org.mule.modules.sqs.SQSConnector;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@MetaDataCategory
public class SendMessageCategory {

    @Inject
    private SQSConnector connector;


    @MetaDataKeyRetriever
    public List<MetaDataKey> metaDataKeys() {
        return new ArrayList<MetaDataKey>(0);
    }

    @MetaDataRetriever
    public MetaData getMetaData(MetaDataKey key) {
        MetaDataModel keyMetaDataModel = new DefaultSimpleMetaDataModel(DataType.STRING);
        MetaDataBuilder<?> valueMetaDataBuilder = new DefaultMetaDataBuilder().createPojo(MessageAttributeValue.class);
        return new DefaultMetaData(new DefaultParameterizedMapMetaDataModel(keyMetaDataModel, valueMetaDataBuilder.build()));
    }

    public SQSConnector getConnector() {
        return connector;
    }

    public void setConnector(SQSConnector connector) {
        this.connector = connector;
    }
}

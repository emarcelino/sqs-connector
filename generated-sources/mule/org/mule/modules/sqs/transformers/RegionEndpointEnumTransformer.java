
package org.mule.modules.sqs.transformers;

import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-14T12:28:26-05:00", comments = "Build M4.1875.17b58a3")
public class RegionEndpointEnumTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;

    public RegionEndpointEnumTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(RegionEndpoint.class);
        setName("RegionEndpointEnumTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        RegionEndpoint result = null;
        result = Enum.valueOf(RegionEndpoint.class, ((String) src));
        return result;
    }

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

}

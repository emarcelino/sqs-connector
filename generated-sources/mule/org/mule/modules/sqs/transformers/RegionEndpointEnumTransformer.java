
package org.mule.modules.sqs.transformers;

import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.modules.sqs.RegionEndpoint;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-19T08:18:01-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
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

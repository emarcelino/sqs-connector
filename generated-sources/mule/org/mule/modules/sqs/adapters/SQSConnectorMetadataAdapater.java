
package org.mule.modules.sqs.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.sqs.SQSConnector;


/**
 * A <code>SQSConnectorMetadataAdapater</code> is a wrapper around {@link SQSConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-19T08:18:01-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
public class SQSConnectorMetadataAdapater
    extends SQSConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Amazon SQS";
    private final static String MODULE_VERSION = "2.3.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.5.0-SNAPSHOT";
    private final static String DEVKIT_BUILD = "UNKNOWN_BUILDNUMBER";
    private final static String MIN_MULE_VERSION = "3.5";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}

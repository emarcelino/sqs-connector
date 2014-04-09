
package org.mule.tooling.ui.contribution;

import javax.annotation.Generated;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-09T09:25:08-05:00", comments = "Build M4.1875.17b58a3")
public class SqsActivator
    extends AbstractUIPlugin
{

    public final static String PLUGIN_ID = "org.mule.tooling.ui.contribution.sqs";
    private static org.mule.tooling.ui.contribution.SqsActivator plugin;

    public void start(BundleContext context)
        throws Exception
    {
        super.start(context);
        plugin = this;
    }

    public void stop(BundleContext context)
        throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    public static org.mule.tooling.ui.contribution.SqsActivator getDefault() {
        return plugin;
    }

}


package org.mule.modules.sqs.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.sqs.SQSConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>SQSConnectorProcessAdapter</code> is a wrapper around {@link SQSConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:16:12-05:00", comments = "Build master.1915.dd1962d")
public class SQSConnectorProcessAdapter
    extends SQSConnectorLifecycleAdapter
    implements ProcessAdapter<SQSConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, SQSConnectorCapabilitiesAdapter> getProcessTemplate() {
        final SQSConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,SQSConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, SQSConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, SQSConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}

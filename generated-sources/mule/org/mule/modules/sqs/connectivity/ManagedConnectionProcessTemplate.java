
package org.mule.modules.sqs.connectivity;

import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessInterceptor;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.sqs.adapters.SQSConnectorConnectionIdentifierAdapter;
import org.mule.modules.sqs.connection.ConnectionManager;
import org.mule.modules.sqs.process.ManagedConnectionProcessInterceptor;
import org.mule.security.oauth.callback.ProcessCallback;
import org.mule.security.oauth.process.ProcessCallbackProcessInterceptor;
import org.mule.security.oauth.process.RetryProcessInterceptor;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-19T08:18:01-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
public class ManagedConnectionProcessTemplate<P >implements ProcessTemplate<P, SQSConnectorConnectionIdentifierAdapter>
{

    private final ProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter> processInterceptor;

    public ManagedConnectionProcessTemplate(ConnectionManager<SQSConnectorConnectionKey, SQSConnectorConnectionIdentifierAdapter> connectionManager, MuleContext muleContext) {
        ProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter>();
        ProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter> managedConnectionProcessInterceptor = new ManagedConnectionProcessInterceptor<P>(processCallbackProcessInterceptor, connectionManager, muleContext);
        ProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter> retryProcessInterceptor = new RetryProcessInterceptor<P, SQSConnectorConnectionIdentifierAdapter>(managedConnectionProcessInterceptor, muleContext, connectionManager.getRetryPolicyTemplate());
        processInterceptor = retryProcessInterceptor;
    }

    public P execute(ProcessCallback<P, SQSConnectorConnectionIdentifierAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, SQSConnectorConnectionIdentifierAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}

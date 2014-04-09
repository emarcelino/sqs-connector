
package org.mule.modules.sqs.sources;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.callback.SourceCallback;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.source.MessageSource;
import org.mule.modules.sqs.SQSConnector;
import org.mule.modules.sqs.connectivity.SQSConnectorConnectionManager;
import org.mule.security.oauth.callback.ProcessCallback;
import org.mule.security.oauth.processor.AbstractListeningMessageProcessor;


/**
 * ReceiveMessagesMessageSource wraps {@link org.mule.modules.sqs.SQSConnector#receiveMessages(org.mule.api.callback.SourceCallback, java.lang.Integer, java.lang.Boolean, java.lang.Long, java.lang.Integer, java.lang.String)} method in {@link SQSConnector } as a message source capable of generating Mule events.  The POJO's method is invoked in its own thread.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-09T09:25:08-05:00", comments = "Build M4.1875.17b58a3")
public class ReceiveMessagesMessageSource
    extends AbstractListeningMessageProcessor
    implements Runnable, FlowConstructAware, MuleContextAware, Startable, Stoppable, MessageSource
{

    protected Object visibilityTimeout;
    protected Integer _visibilityTimeoutType;
    protected Object preserveMessages;
    protected Boolean _preserveMessagesType;
    protected Object pollPeriod;
    protected Long _pollPeriodType;
    protected Object numberOfMessages;
    protected Integer _numberOfMessagesType;
    protected Object queueUrl;
    protected String _queueUrlType;
    protected Object accessKey;
    protected String _accessKeyType;
    protected Object secretKey;
    protected String _secretKeyType;
    protected Object queueName;
    protected String _queueNameType;
    /**
     * Thread under which this message source will execute
     * 
     */
    private Thread thread;

    public ReceiveMessagesMessageSource(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    /**
     * Sets visibilityTimeout
     * 
     * @param value Value to set
     */
    public void setVisibilityTimeout(Object value) {
        this.visibilityTimeout = value;
    }

    /**
     * Sets pollPeriod
     * 
     * @param value Value to set
     */
    public void setPollPeriod(Object value) {
        this.pollPeriod = value;
    }

    /**
     * Sets preserveMessages
     * 
     * @param value Value to set
     */
    public void setPreserveMessages(Object value) {
        this.preserveMessages = value;
    }

    /**
     * Sets numberOfMessages
     * 
     * @param value Value to set
     */
    public void setNumberOfMessages(Object value) {
        this.numberOfMessages = value;
    }

    /**
     * Sets queueUrl
     * 
     * @param value Value to set
     */
    public void setQueueUrl(Object value) {
        this.queueUrl = value;
    }

    /**
     * Sets accessKey
     * 
     * @param value Value to set
     */
    public void setAccessKey(Object value) {
        this.accessKey = value;
    }

    /**
     * Sets secretKey
     * 
     * @param value Value to set
     */
    public void setSecretKey(Object value) {
        this.secretKey = value;
    }

    /**
     * Sets queueName
     * 
     * @param value Value to set
     */
    public void setQueueName(Object value) {
        this.queueName = value;
    }

    /**
     * Method to be called when Mule instance gets started.
     * 
     */
    public void start()
        throws MuleException
    {
        if (thread == null) {
            thread = new Thread(this, "Receiving Thread");
        }
        thread.start();
    }

    /**
     * Method to be called when Mule instance gets stopped.
     * 
     */
    public void stop()
        throws MuleException
    {
        thread.interrupt();
    }

    /**
     * Implementation {@link Runnable#run()} that will invoke the method on the pojo that this message source wraps.
     * 
     */
    public void run() {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(SQSConnectorConnectionManager.class, true, null);
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            final SourceCallback sourceCallback = this;
            final Integer transformedVisibilityTimeout = ((Integer) transform(getMuleContext(), ((MuleEvent) null), getClass().getDeclaredField("_visibilityTimeoutType").getGenericType(), null, visibilityTimeout));
            final Boolean transformedPreserveMessages = ((Boolean) transform(getMuleContext(), ((MuleEvent) null), getClass().getDeclaredField("_preserveMessagesType").getGenericType(), null, preserveMessages));
            final Long transformedPollPeriod = ((Long) transform(getMuleContext(), ((MuleEvent) null), getClass().getDeclaredField("_pollPeriodType").getGenericType(), null, pollPeriod));
            final Integer transformedNumberOfMessages = ((Integer) transform(getMuleContext(), ((MuleEvent) null), getClass().getDeclaredField("_numberOfMessagesType").getGenericType(), null, numberOfMessages));
            final String transformedQueueUrl = ((String) transform(getMuleContext(), ((MuleEvent) null), getClass().getDeclaredField("_queueUrlType").getGenericType(), null, queueUrl));
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((SQSConnector) object).receiveMessages(sourceCallback, transformedVisibilityTimeout, transformedPreserveMessages, transformedPollPeriod, transformedNumberOfMessages, transformedQueueUrl);
                    return null;
                }

            }
            , null, ((MuleEvent) null));
        } catch (MessagingException e) {
            getFlowConstruct().getExceptionListener().handleException(e, e.getEvent());
        } catch (Exception e) {
            getMuleContext().getExceptionListener().handleException(e);
        }
    }

}

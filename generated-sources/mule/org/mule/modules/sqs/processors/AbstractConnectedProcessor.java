
package org.mule.modules.sqs.processors;

import java.lang.reflect.Type;
import javax.annotation.Generated;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:43:51-05:00", comments = "Build master.1915.dd1962d")
public abstract class AbstractConnectedProcessor
    extends DevkitBasedMessageProcessor
    implements ConnectivityProcessor
{

    protected Object accessKey;
    protected String _accessKeyType;
    protected Object secretKey;
    protected String _secretKeyType;
    protected Object queueName;
    protected String _queueNameType;

    public AbstractConnectedProcessor(String operationName) {
        super(operationName);
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
     * Retrieves accessKey
     * 
     */
    @Override
    public Object getAccessKey() {
        return this.accessKey;
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
     * Retrieves secretKey
     * 
     */
    @Override
    public Object getSecretKey() {
        return this.secretKey;
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
     * Retrieves queueName
     * 
     */
    @Override
    public Object getQueueName() {
        return this.queueName;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Type typeFor(String fieldName)
        throws NoSuchFieldException
    {
        return AbstractConnectedProcessor.class.getDeclaredField(fieldName).getGenericType();
    }

}

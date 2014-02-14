
package org.mule.modules.sqs.processors;

import javax.annotation.Generated;
import org.mule.streaming.processor.AbstractDevkitBasedPageableMessageProcessor;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-14T11:49:58-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
public abstract class AbstractPagedConnectedProcessor
    extends AbstractDevkitBasedPageableMessageProcessor
{

    protected Object accessKey;
    protected String _accessKeyType;
    protected Object secretKey;
    protected String _secretKeyType;
    protected Object queueName;
    protected String _queueNameType;

    public AbstractPagedConnectedProcessor(String operationName) {
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
    public Object getQueueName() {
        return this.queueName;
    }

}

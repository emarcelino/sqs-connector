
package org.mule.modules.sqs.connectivity;

import javax.annotation.Generated;


/**
 * A tuple of connection parameters
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-14T12:28:26-05:00", comments = "Build M4.1875.17b58a3")
public class SQSConnectorConnectionKey {

    /**
     * 
     */
    private String accessKey;
    /**
     * 
     */
    private String secretKey;
    /**
     * 
     */
    private String queueName;

    public SQSConnectorConnectionKey(String accessKey, String secretKey, String queueName) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.queueName = queueName;
    }

    /**
     * Sets accessKey
     * 
     * @param value Value to set
     */
    public void setAccessKey(String value) {
        this.accessKey = value;
    }

    /**
     * Retrieves accessKey
     * 
     */
    public String getAccessKey() {
        return this.accessKey;
    }

    /**
     * Sets secretKey
     * 
     * @param value Value to set
     */
    public void setSecretKey(String value) {
        this.secretKey = value;
    }

    /**
     * Retrieves secretKey
     * 
     */
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * Sets queueName
     * 
     * @param value Value to set
     */
    public void setQueueName(String value) {
        this.queueName = value;
    }

    /**
     * Retrieves queueName
     * 
     */
    public String getQueueName() {
        return this.queueName;
    }

    @Override
    public int hashCode() {
        int result = ((this.accessKey!= null)?this.accessKey.hashCode(): 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SQSConnectorConnectionKey)) {
            return false;
        }
        SQSConnectorConnectionKey that = ((SQSConnectorConnectionKey) o);
        if (((this.accessKey!= null)?(!this.accessKey.equals(that.accessKey)):(that.accessKey!= null))) {
            return false;
        }
        return true;
    }

}

package org.mule.modules.sqs.connection.strategy;

import org.apache.commons.lang.StringUtils;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.sqs.RegionEndpoint;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

@ReconnectOn(exceptions = {AmazonClientException.class})
@ConnectionManagement(friendlyName="Connection Management")
public class SQSConnectionManagement {

    /**
     * The queue URL to connect to. It takes priority over the queue name defined
     * in the connection parameters.
     */
    @Optional
    @Configurable
    @Placement(group = "Optional Parameters") @FriendlyName("Queue URL")
    private String queueUrl;

	private String accessKey;

    /**
     * Queue Region
     */
    @Optional
    @Configurable
    @Placement(group = "Optional Parameters") @FriendlyName("Region Endpoint")
    private RegionEndpoint region;
	
    /**
     * Message Queue
     */
    private AmazonSQSClient msgQueue;
    public AmazonSQSClient getMsgQueue() {
		return msgQueue;
	}

	public void setMsgQueue(AmazonSQSClient msgQueue) {
		this.msgQueue = msgQueue;
	}

	public AmazonSQSAsync getMsgQueueAsync() {
		return msgQueueAsync;
	}

	public void setMsgQueueAsync(AmazonSQSAsync msgQueueAsync) {
		this.msgQueueAsync = msgQueueAsync;
	}

	private AmazonSQSAsync msgQueueAsync;


    /**
     * @param accessKey AWS access key
     * @param secretKey AWS secret key
     * @param queueName The name of the queue to connect to (optional)
     * @throws ConnectionException If a connection cannot be made
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String accessKey, String secretKey, @Optional String queueName)
             throws ConnectionException {
        try {
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            msgQueue = new AmazonSQSClient(credentials);
            msgQueueAsync = new AmazonSQSAsyncClient(credentials);

            if(region != null) {
                msgQueue.setEndpoint(region.value());
                msgQueueAsync.setEndpoint(region.value());
            }
            if (queueName != null) {
	            CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
	            setQueueUrl(msgQueue.createQueue(createQueueRequest).getQueueUrl());
            } else if (queueUrl != null) {
            	setQueueUrl(queueUrl);
            } else {
            	throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, null, "A queue name or queue URL must be specified to make a connection.");
            }
        } catch (Exception e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, null, e.getMessage(), e);
        }
        setAccessKey(accessKey);
    }

    public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

    @ConnectionIdentifier
    public String getAccessKey() {
    	return this.accessKey;
    }

	@Disconnect
    public void disconnect() {
        msgQueue = null;
    }

    @ValidateConnection
    public boolean isConnected() {
        return this.msgQueue != null;
    }
    
    public String getUrl() {
        return this.queueUrl;
    }

    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }
    
    public String getQueueUrl(String queueParameter) {
        if (StringUtils.isNotEmpty(queueParameter)) {
        	return queueParameter;
        }
        if (StringUtils.isNotEmpty(this.queueUrl)) {
        	return this.queueUrl;
        }
        else {
        	return null;
        }
    }
    
    public RegionEndpoint getRegion() {
        return region;
    }

    public void setRegion(RegionEndpoint region) {
        this.region = region;
    }

}

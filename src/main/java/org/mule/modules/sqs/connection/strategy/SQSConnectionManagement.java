/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.connection.strategy;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import org.apache.commons.lang.StringUtils;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.*;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.sqs.RegionEndpoint;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ReconnectOn(exceptions = {AmazonClientException.class})
@ConnectionManagement(friendlyName = "Configuration")
public class SQSConnectionManagement {

    /**
     * The queue URL to connect to. It takes priority over the queue name defined
     * in the connection parameters.
     */
    @Optional
    @Configurable
    @Placement(group = "Optional Parameters")
    @FriendlyName("Queue URL")
    private String queueUrl;

    private String accessKey;

    /**
     * Queue Region
     */
    @Optional
    @Configurable
    @Placement(group = "Optional Parameters")
    @FriendlyName("Region Endpoint")
    private RegionEndpoint region;

    /**
     * The optional communication protocol to use when sending requests to AWS.
     * Communication over HTTPS is the default
     */
    @Configurable
    @Optional
    private Protocol protocol;

    /**
     * The optional proxy port
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private String proxyHost;

    /**
     * The optional proxy port
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private Integer proxyPort;

    /**
     * The optional proxy username
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private String proxyUsername;

    /**
     * The optional proxy password
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private String proxyPassword;

    /**
     * The optional proxy domain
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private String proxyDomain;

    /**
     * The optional proxy workstation
     */
    @Configurable
    @Optional
    @Placement(group = "Proxy Settings")
    private String proxyWorkstation;

    /**
     * The amount of time to wait (in milliseconds) for data to be transferred
     * over an established, open connection before the connection is timed out.
     * A value of 0 means infinity, and is not recommended.
     */
    @Configurable
    @Default("50000")
    private Integer socketTimeout;

    /**
     * The amount of time to wait (in milliseconds) when initially establishing
     * a connection before giving up and timing out. A value of 0 means
     * infinity, and is not recommended.
     */
    @Configurable
    @Default("50000")
    private Integer connectionTimeout;

    /**
     * Message Queue
     */
    private AmazonSQSClient msgQueue;
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
            msgQueue = new AmazonSQSClient(credentials, clientConfiguration());
            msgQueueAsync = new AmazonSQSAsyncClient(credentials, clientConfiguration(), (ExecutorService) Executors.newFixedThreadPool(50));

            if (region != null) {
                msgQueue.setEndpoint(region.value());
                msgQueueAsync.setEndpoint(region.value());
            }
            if (queueName != null) {
                CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
                setQueueUrl(msgQueue.createQueue(createQueueRequest).getQueueUrl());
            } else if (queueUrl != null) {
                setQueueUrl(queueUrl);
                msgQueue.getQueueAttributes(queueUrl, Arrays.asList("All"));
            } else {
                throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, null, "A queue name or queue URL must be specified to make a connection.");
            }
        } catch (Exception e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, null, e.getMessage(), e);
        }
        setAccessKey(accessKey);
    }

    @ConnectionIdentifier
    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Disconnect
    public void disconnect() {
        msgQueue = null;
        msgQueueAsync = null;
    }

    @ValidateConnection
    public boolean isConnected() {
        return this.msgQueue != null || this.msgQueueAsync != null;
    }

    public String getUrl() {
        return this.queueUrl;
    }

    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    public String getQueueUrl(String queueUrl) {
        if (StringUtils.isNotEmpty(queueUrl)) {
            return queueUrl;
        }
        if (StringUtils.isNotEmpty(this.queueUrl)) {
            return this.queueUrl;
        } else {
            return null;
        }
    }

    private ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfig = new ClientConfiguration();

        if (StringUtils.isNotBlank(getProxyUsername())) {
            clientConfig.setProxyUsername(getProxyUsername());
        }
        if (getProxyPort() != null) {
            clientConfig.setProxyPort(proxyPort);
        }
        if (StringUtils.isNotBlank(getProxyPassword())) {
            clientConfig.setProxyPassword(getProxyPassword());
        }
        if (StringUtils.isNotBlank(getProxyHost())) {
            clientConfig.setProxyHost(getProxyHost());
        }
        if (StringUtils.isNotBlank(getProxyDomain())) {
            clientConfig.setProxyDomain(getProxyDomain());
        }
        if (StringUtils.isNotBlank(getProxyWorkstation())) {
            clientConfig.setProxyWorkstation(getProxyWorkstation());
        }
        if (getProtocol() != null) {
            clientConfig.setProtocol(getProtocol());
        }
        if (getConnectionTimeout() != null) {
            clientConfig.setConnectionTimeout(getConnectionTimeout());
        }
        if (getSocketTimeout() != null) {
            clientConfig.setSocketTimeout(getSocketTimeout());
        }

        return clientConfig;
    }

    public RegionEndpoint getRegion() {
        return region;
    }

    public void setRegion(RegionEndpoint region) {
        this.region = region;
    }

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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public String getProxyDomain() {
        return proxyDomain;
    }

    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    public String getProxyWorkstation() {
        return proxyWorkstation;
    }

    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}

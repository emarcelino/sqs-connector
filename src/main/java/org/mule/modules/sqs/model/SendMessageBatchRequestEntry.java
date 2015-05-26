/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

/**
 * <p>
 * Contains the details of a single Amazon SQS message along with a
 * <code>Id</code> .
 * </p>
 */
public class SendMessageBatchRequestEntry implements Serializable {

    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     */
    private String id;

    /**
     * Body of the message.
     */
    private String messageBody;

    /**
     * The number of seconds for which the message has to be delayed.
     */
    private Integer delaySeconds;

    /**
     * Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     */
    private java.util.Map<String, MessageAttributeValue> messageAttributes;

    /**
     * Default constructor for a new SendMessageBatchRequestEntry object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public SendMessageBatchRequestEntry() {
    }

    /**
     * Constructs a new SendMessageBatchRequestEntry object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param id          An identifier for the message in this batch. This is used to
     *                    communicate the result. Note that the <code>Id</code>s of a batch
     *                    request need to be unique within the request.
     * @param messageBody Body of the message.
     */
    public SendMessageBatchRequestEntry(String id, String messageBody) {
        setId(id);
        setMessageBody(messageBody);
    }

    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @return An identifier for the message in this batch. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     */
    public String getId() {
        return id;
    }

    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @param id An identifier for the message in this batch. This is used to
     *           communicate the result. Note that the <code>Id</code>s of a batch
     *           request need to be unique within the request.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id An identifier for the message in this batch. This is used to
     *           communicate the result. Note that the <code>Id</code>s of a batch
     *           request need to be unique within the request.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchRequestEntry withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Body of the message.
     *
     * @return Body of the message.
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * Body of the message.
     *
     * @param messageBody Body of the message.
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    /**
     * Body of the message.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param messageBody Body of the message.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchRequestEntry withMessageBody(String messageBody) {
        this.messageBody = messageBody;
        return this;
    }

    /**
     * The number of seconds for which the message has to be delayed.
     *
     * @return The number of seconds for which the message has to be delayed.
     */
    public Integer getDelaySeconds() {
        return delaySeconds;
    }

    /**
     * The number of seconds for which the message has to be delayed.
     *
     * @param delaySeconds The number of seconds for which the message has to be delayed.
     */
    public void setDelaySeconds(Integer delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    /**
     * The number of seconds for which the message has to be delayed.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param delaySeconds The number of seconds for which the message has to be delayed.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchRequestEntry withDelaySeconds(Integer delaySeconds) {
        this.delaySeconds = delaySeconds;
        return this;
    }

    /**
     * Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     *
     * @return Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     */
    public java.util.Map<String, MessageAttributeValue> getMessageAttributes() {

        if (messageAttributes == null) {
            messageAttributes = new java.util.HashMap<String, MessageAttributeValue>();
        }
        return messageAttributes;
    }

    /**
     * Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     *
     * @param messageAttributes Each message attribute consists of a Name, Type, and Value. For more
     *                          information, see <a
     *                          href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     *                          Attribute Items</a>.
     */
    public void setMessageAttributes(java.util.Map<String, MessageAttributeValue> messageAttributes) {
        this.messageAttributes = messageAttributes;
    }

    /**
     * Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param messageAttributes Each message attribute consists of a Name, Type, and Value. For more
     *                          information, see <a
     *                          href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     *                          Attribute Items</a>.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchRequestEntry withMessageAttributes(java.util.Map<String, MessageAttributeValue> messageAttributes) {
        setMessageAttributes(messageAttributes);
        return this;
    }

    /**
     * Each message attribute consists of a Name, Type, and Value. For more
     * information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributesNTV">Message
     * Attribute Items</a>.
     * <p/>
     * The method adds a new key-value pair into MessageAttributes parameter,
     * and returns a reference to this object so that method calls can be
     * chained together.
     *
     * @param key   The key of the entry to be added into MessageAttributes.
     * @param value The corresponding value of the entry to be added into MessageAttributes.
     */
    public SendMessageBatchRequestEntry addMessageAttributesEntry(String key, MessageAttributeValue value) {
        if (null == this.messageAttributes) {
            this.messageAttributes = new java.util.HashMap<String, MessageAttributeValue>();
        }
        if (this.messageAttributes.containsKey(key))
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        this.messageAttributes.put(key, value);
        return this;
    }

    /**
     * Removes all the entries added into MessageAttributes.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     */
    public SendMessageBatchRequestEntry clearMessageAttributesEntries() {
        this.messageAttributes = null;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getId() != null) sb.append("Id: " + getId() + ",");
        if (getMessageBody() != null) sb.append("MessageBody: " + getMessageBody() + ",");
        if (getDelaySeconds() != null) sb.append("DelaySeconds: " + getDelaySeconds() + ",");
        if (getMessageAttributes() != null) sb.append("MessageAttributes: " + getMessageAttributes());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendMessageBatchRequestEntry that = (SendMessageBatchRequestEntry) o;

        if (!getId().equals(that.getId())) return false;
        if (!getMessageBody().equals(that.getMessageBody())) return false;
        if (!getDelaySeconds().equals(that.getDelaySeconds())) return false;
        return getMessageAttributes().equals(that.getMessageAttributes());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getMessageBody().hashCode();
        result = 31 * result + getDelaySeconds().hashCode();
        result = 31 * result + getMessageAttributes().hashCode();
        return result;
    }
}
    
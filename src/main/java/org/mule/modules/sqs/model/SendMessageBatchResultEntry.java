/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

/**
 * <p>
 * Encloses a message ID for successfully enqueued message of a
 * SendMessageBatch.
 * </p>
 */
public class SendMessageBatchResultEntry implements Serializable {

    /**
     * An identifier for the message in this batch.
     */
    private String id;

    /**
     * An identifier for the message.
     */
    private String messageId;

    /**
     * An MD5 digest of the non-URL-encoded message body string. This can be
     * used to verify that Amazon SQS received the message correctly. Amazon
     * SQS first URL decodes the message before creating the MD5 digest. For
     * information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    private String mD5OfMessageBody;

    /**
     * An MD5 digest of the non-URL-encoded message attribute string. This
     * can be used to verify that Amazon SQS received the message batch
     * correctly. Amazon SQS first URL decodes the message before creating
     * the MD5 digest. For information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    private String mD5OfMessageAttributes;

    /**
     * An identifier for the message in this batch.
     *
     * @return An identifier for the message in this batch.
     */
    public String getId() {
        return id;
    }
    
    /**
     * An identifier for the message in this batch.
     *
     * @param id An identifier for the message in this batch.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * An identifier for the message in this batch.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id An identifier for the message in this batch.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public SendMessageBatchResultEntry withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * An identifier for the message.
     *
     * @return An identifier for the message.
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * An identifier for the message.
     *
     * @param messageId An identifier for the message.
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    /**
     * An identifier for the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param messageId An identifier for the message.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public SendMessageBatchResultEntry withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    /**
     * An MD5 digest of the non-URL-encoded message body string. This can be
     * used to verify that Amazon SQS received the message correctly. Amazon
     * SQS first URL decodes the message before creating the MD5 digest. For
     * information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @return An MD5 digest of the non-URL-encoded message body string. This can be
     *         used to verify that Amazon SQS received the message correctly. Amazon
     *         SQS first URL decodes the message before creating the MD5 digest. For
     *         information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    public String getMD5OfMessageBody() {
        return mD5OfMessageBody;
    }
    
    /**
     * An MD5 digest of the non-URL-encoded message body string. This can be
     * used to verify that Amazon SQS received the message correctly. Amazon
     * SQS first URL decodes the message before creating the MD5 digest. For
     * information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @param mD5OfMessageBody An MD5 digest of the non-URL-encoded message body string. This can be
     *         used to verify that Amazon SQS received the message correctly. Amazon
     *         SQS first URL decodes the message before creating the MD5 digest. For
     *         information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    public void setMD5OfMessageBody(String mD5OfMessageBody) {
        this.mD5OfMessageBody = mD5OfMessageBody;
    }
    
    /**
     * An MD5 digest of the non-URL-encoded message body string. This can be
     * used to verify that Amazon SQS received the message correctly. Amazon
     * SQS first URL decodes the message before creating the MD5 digest. For
     * information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param mD5OfMessageBody An MD5 digest of the non-URL-encoded message body string. This can be
     *         used to verify that Amazon SQS received the message correctly. Amazon
     *         SQS first URL decodes the message before creating the MD5 digest. For
     *         information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public SendMessageBatchResultEntry withMD5OfMessageBody(String mD5OfMessageBody) {
        this.mD5OfMessageBody = mD5OfMessageBody;
        return this;
    }

    /**
     * An MD5 digest of the non-URL-encoded message attribute string. This
     * can be used to verify that Amazon SQS received the message batch
     * correctly. Amazon SQS first URL decodes the message before creating
     * the MD5 digest. For information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @return An MD5 digest of the non-URL-encoded message attribute string. This
     *         can be used to verify that Amazon SQS received the message batch
     *         correctly. Amazon SQS first URL decodes the message before creating
     *         the MD5 digest. For information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    public String getMD5OfMessageAttributes() {
        return mD5OfMessageAttributes;
    }
    
    /**
     * An MD5 digest of the non-URL-encoded message attribute string. This
     * can be used to verify that Amazon SQS received the message batch
     * correctly. Amazon SQS first URL decodes the message before creating
     * the MD5 digest. For information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @param mD5OfMessageAttributes An MD5 digest of the non-URL-encoded message attribute string. This
     *         can be used to verify that Amazon SQS received the message batch
     *         correctly. Amazon SQS first URL decodes the message before creating
     *         the MD5 digest. For information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     */
    public void setMD5OfMessageAttributes(String mD5OfMessageAttributes) {
        this.mD5OfMessageAttributes = mD5OfMessageAttributes;
    }
    
    /**
     * An MD5 digest of the non-URL-encoded message attribute string. This
     * can be used to verify that Amazon SQS received the message batch
     * correctly. Amazon SQS first URL decodes the message before creating
     * the MD5 digest. For information about MD5, go to <a
     * href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param mD5OfMessageAttributes An MD5 digest of the non-URL-encoded message attribute string. This
     *         can be used to verify that Amazon SQS received the message batch
     *         correctly. Amazon SQS first URL decodes the message before creating
     *         the MD5 digest. For information about MD5, go to <a
     *         href="http://www.faqs.org/rfcs/rfc1321.html">http://www.faqs.org/rfcs/rfc1321.html</a>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public SendMessageBatchResultEntry withMD5OfMessageAttributes(String mD5OfMessageAttributes) {
        this.mD5OfMessageAttributes = mD5OfMessageAttributes;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getId() != null) sb.append("Id: " + getId() + ",");
        if (getMessageId() != null) sb.append("MessageId: " + getMessageId() + ",");
        if (getMD5OfMessageBody() != null) sb.append("MD5OfMessageBody: " + getMD5OfMessageBody() + ",");
        if (getMD5OfMessageAttributes() != null) sb.append("MD5OfMessageAttributes: " + getMD5OfMessageAttributes() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getId() == null) ? 0 : getId().hashCode()); 
        hashCode = prime * hashCode + ((getMessageId() == null) ? 0 : getMessageId().hashCode()); 
        hashCode = prime * hashCode + ((getMD5OfMessageBody() == null) ? 0 : getMD5OfMessageBody().hashCode()); 
        hashCode = prime * hashCode + ((getMD5OfMessageAttributes() == null) ? 0 : getMD5OfMessageAttributes().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof SendMessageBatchResultEntry == false) return false;
        SendMessageBatchResultEntry other = (SendMessageBatchResultEntry)obj;
        
        if (other.getId() == null ^ this.getId() == null) return false;
        if (other.getId() != null && other.getId().equals(this.getId()) == false) return false; 
        if (other.getMessageId() == null ^ this.getMessageId() == null) return false;
        if (other.getMessageId() != null && other.getMessageId().equals(this.getMessageId()) == false) return false; 
        if (other.getMD5OfMessageBody() == null ^ this.getMD5OfMessageBody() == null) return false;
        if (other.getMD5OfMessageBody() != null && other.getMD5OfMessageBody().equals(this.getMD5OfMessageBody()) == false) return false; 
        if (other.getMD5OfMessageAttributes() == null ^ this.getMD5OfMessageAttributes() == null) return false;
        if (other.getMD5OfMessageAttributes() != null && other.getMD5OfMessageAttributes().equals(this.getMD5OfMessageAttributes()) == false) return false; 
        return true;
    }
    
}
    
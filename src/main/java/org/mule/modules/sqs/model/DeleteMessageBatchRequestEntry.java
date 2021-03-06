/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

/**
 * <p>
 * Encloses a receipt handle and an identifier for it.
 * </p>
 */
public class DeleteMessageBatchRequestEntry implements Serializable {

    /**
     * An identifier for this particular receipt handle. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     */
    private String id;

    /**
     * A receipt handle.
     */
    private String receiptHandle;

    /**
     * Default constructor for a new DeleteMessageBatchRequestEntry object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeleteMessageBatchRequestEntry() {
    }

    /**
     * Constructs a new DeleteMessageBatchRequestEntry object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param id            An identifier for this particular receipt handle. This is
     *                      used to communicate the result. Note that the <code>Id</code>s of a
     *                      batch request need to be unique within the request.
     * @param receiptHandle A receipt handle.
     */
    public DeleteMessageBatchRequestEntry(String id, String receiptHandle) {
        setId(id);
        setReceiptHandle(receiptHandle);
    }

    /**
     * An identifier for this particular receipt handle. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @return An identifier for this particular receipt handle. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     */
    public String getId() {
        return id;
    }

    /**
     * An identifier for this particular receipt handle. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @param id An identifier for this particular receipt handle. This is used to
     *           communicate the result. Note that the <code>Id</code>s of a batch
     *           request need to be unique within the request.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * An identifier for this particular receipt handle. This is used to
     * communicate the result. Note that the <code>Id</code>s of a batch
     * request need to be unique within the request.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id An identifier for this particular receipt handle. This is used to
     *           communicate the result. Note that the <code>Id</code>s of a batch
     *           request need to be unique within the request.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public DeleteMessageBatchRequestEntry withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * A receipt handle.
     *
     * @return A receipt handle.
     */
    public String getReceiptHandle() {
        return receiptHandle;
    }

    /**
     * A receipt handle.
     *
     * @param receiptHandle A receipt handle.
     */
    public void setReceiptHandle(String receiptHandle) {
        this.receiptHandle = receiptHandle;
    }

    /**
     * A receipt handle.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param receiptHandle A receipt handle.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public DeleteMessageBatchRequestEntry withReceiptHandle(String receiptHandle) {
        this.receiptHandle = receiptHandle;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getId() != null)
            sb.append("Id: " + getId() + ",");
        if (getReceiptHandle() != null)
            sb.append("ReceiptHandle: " + getReceiptHandle());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getId() == null) ? 0 : getId().hashCode());
        hashCode = prime * hashCode + ((getReceiptHandle() == null) ? 0 : getReceiptHandle().hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof DeleteMessageBatchRequestEntry == false) return false;
        DeleteMessageBatchRequestEntry other = (DeleteMessageBatchRequestEntry) obj;

        if (other.getId() == null ^ this.getId() == null) return false;
        if (other.getId() != null && other.getId().equals(this.getId()) == false) return false;
        if (other.getReceiptHandle() == null ^ this.getReceiptHandle() == null) return false;
        if (other.getReceiptHandle() != null && other.getReceiptHandle().equals(this.getReceiptHandle()) == false)
            return false;
        return true;
    }

}
    
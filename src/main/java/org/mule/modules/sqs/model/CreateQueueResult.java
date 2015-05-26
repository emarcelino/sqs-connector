/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

/**
 * <p>
 * Returns the QueueUrl element of the created queue.
 * </p>
 */
public class CreateQueueResult implements Serializable {

    /**
     * The URL for the created Amazon SQS queue.
     */
    private String queueUrl;

    /**
     * The URL for the created Amazon SQS queue.
     *
     * @return The URL for the created Amazon SQS queue.
     */
    public String getQueueUrl() {
        return queueUrl;
    }

    /**
     * The URL for the created Amazon SQS queue.
     *
     * @param queueUrl The URL for the created Amazon SQS queue.
     */
    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    /**
     * The URL for the created Amazon SQS queue.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueUrl The URL for the created Amazon SQS queue.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public CreateQueueResult withQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
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
        if (getQueueUrl() != null) sb.append("QueueUrl: " + getQueueUrl());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getQueueUrl() == null) ? 0 : getQueueUrl().hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof CreateQueueResult == false) return false;
        CreateQueueResult other = (CreateQueueResult) obj;

        if (other.getQueueUrl() == null ^ this.getQueueUrl() == null) return false;
        if (other.getQueueUrl() != null && other.getQueueUrl().equals(this.getQueueUrl()) == false) return false;
        return true;
    }

}
    
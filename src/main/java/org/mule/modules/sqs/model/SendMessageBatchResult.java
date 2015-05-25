/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * For each message in the batch, the response contains a
 * SendMessageBatchResultEntry tag if the message succeeds or a
 * BatchResultErrorEntry tag if the message fails.
 * </p>
 */
public class SendMessageBatchResult implements Serializable {

    /**
     * A list of <a>SendMessageBatchResultEntry</a> items.
     */
    private List<SendMessageBatchResultEntry> successful;

    /**
     * A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     */
    private List<BatchResultErrorEntry> failed;

    /**
     * A list of <a>SendMessageBatchResultEntry</a> items.
     *
     * @return A list of <a>SendMessageBatchResultEntry</a> items.
     */
    public java.util.List<SendMessageBatchResultEntry> getSuccessful() {
        if (successful == null) {
            successful = new ArrayList<SendMessageBatchResultEntry>();
        }
        return successful;
    }

    /**
     * A list of <a>SendMessageBatchResultEntry</a> items.
     *
     * @param successful A list of <a>SendMessageBatchResultEntry</a> items.
     */
    public void setSuccessful(java.util.Collection<SendMessageBatchResultEntry> successful) {
        if (successful == null) {
            this.successful = null;
            return;
        }
        List<SendMessageBatchResultEntry> successfulCopy = new ArrayList<SendMessageBatchResultEntry>(successful.size());
        successfulCopy.addAll(successful);
        this.successful = successfulCopy;
    }

    /**
     * A list of <a>SendMessageBatchResultEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param successful A list of <a>SendMessageBatchResultEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchResult withSuccessful(SendMessageBatchResultEntry... successful) {
        if (getSuccessful() == null) setSuccessful(new ArrayList<SendMessageBatchResultEntry>(successful.length));
        for (SendMessageBatchResultEntry value : successful) {
            getSuccessful().add(value);
        }
        return this;
    }

    /**
     * A list of <a>SendMessageBatchResultEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param successful A list of <a>SendMessageBatchResultEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchResult withSuccessful(Collection<SendMessageBatchResultEntry> successful) {
        if (successful == null) {
            this.successful = null;
        } else {
            List<SendMessageBatchResultEntry> successfulCopy = new ArrayList<SendMessageBatchResultEntry>(successful.size());
            successfulCopy.addAll(successful);
            this.successful = successfulCopy;
        }

        return this;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     *
     * @return A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     */
    public java.util.List<BatchResultErrorEntry> getFailed() {
        if (failed == null) {
            failed = new ArrayList<BatchResultErrorEntry>();
        }
        return failed;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items with the error detail
     *               about each message that could not be enqueued.
     */
    public void setFailed(java.util.Collection<BatchResultErrorEntry> failed) {
        if (failed == null) {
            this.failed = null;
            return;
        }
        List<BatchResultErrorEntry> failedCopy = new ArrayList<BatchResultErrorEntry>(failed.size());
        failedCopy.addAll(failed);
        this.failed = failedCopy;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items with the error detail
     *               about each message that could not be enqueued.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchResult withFailed(BatchResultErrorEntry... failed) {
        if (getFailed() == null) setFailed(new java.util.ArrayList<BatchResultErrorEntry>(failed.length));
        for (BatchResultErrorEntry value : failed) {
            getFailed().add(value);
        }
        return this;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items with the error detail
     * about each message that could not be enqueued.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items with the error detail
     *               about each message that could not be enqueued.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public SendMessageBatchResult withFailed(java.util.Collection<BatchResultErrorEntry> failed) {
        if (failed == null) {
            this.failed = null;
        } else {
            List<BatchResultErrorEntry> failedCopy = new ArrayList<BatchResultErrorEntry>(failed.size());
            failedCopy.addAll(failed);
            this.failed = failedCopy;
        }

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
        if (getSuccessful() != null) sb.append("Successful: " + getSuccessful() + ",");
        if (getFailed() != null) sb.append("Failed: " + getFailed());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getSuccessful() == null) ? 0 : getSuccessful().hashCode());
        hashCode = prime * hashCode + ((getFailed() == null) ? 0 : getFailed().hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof SendMessageBatchResult == false) return false;
        SendMessageBatchResult other = (SendMessageBatchResult) obj;

        if (other.getSuccessful() == null ^ this.getSuccessful() == null) return false;
        if (other.getSuccessful() != null && other.getSuccessful().equals(this.getSuccessful()) == false) return false;
        if (other.getFailed() == null ^ this.getFailed() == null) return false;
        if (other.getFailed() != null && other.getFailed().equals(this.getFailed()) == false) return false;
        return true;
    }

}
    
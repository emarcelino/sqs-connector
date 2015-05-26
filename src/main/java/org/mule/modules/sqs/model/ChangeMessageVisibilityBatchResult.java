/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * For each message in the batch, the response contains a
 * ChangeMessageVisibilityBatchResultEntry tag if the message succeeds or
 * a BatchResultErrorEntry tag if the message fails.
 * </p>
 */
public class ChangeMessageVisibilityBatchResult implements Serializable {

    /**
     * A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     */
    private List<ChangeMessageVisibilityBatchResultEntry> successful = null;

    /**
     * A list of <a>BatchResultErrorEntry</a> items.
     */
    private List<BatchResultErrorEntry> failed = null;

    /**
     * A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     *
     * @return A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     */
    public List<ChangeMessageVisibilityBatchResultEntry> getSuccessful() {
        if (successful == null) {
            successful = new ArrayList<ChangeMessageVisibilityBatchResultEntry>();
        }
        return successful;
    }

    /**
     * A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     *
     * @param successful A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     */
    public void setSuccessful(java.util.Collection<ChangeMessageVisibilityBatchResultEntry> successful) {
        if (successful == null) {
            this.successful = null;
            return;
        }
        List<ChangeMessageVisibilityBatchResultEntry> successfulCopy = new ArrayList<ChangeMessageVisibilityBatchResultEntry>(successful.size());
        successfulCopy.addAll(successful);
        this.successful = successfulCopy;
    }

    /**
     * A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param successful A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ChangeMessageVisibilityBatchResult withSuccessful(ChangeMessageVisibilityBatchResultEntry... successful) {
        for (ChangeMessageVisibilityBatchResultEntry value : successful) {
            getSuccessful().add(value);
        }
        return this;
    }

    /**
     * A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param successful A list of <a>ChangeMessageVisibilityBatchResultEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ChangeMessageVisibilityBatchResult withSuccessful(java.util.Collection<ChangeMessageVisibilityBatchResultEntry> successful) {
        if (successful == null) {
            this.successful = null;
        } else {
            List<ChangeMessageVisibilityBatchResultEntry> successfulCopy = new ArrayList<ChangeMessageVisibilityBatchResultEntry>(successful.size());
            successfulCopy.addAll(successful);
            this.successful = successfulCopy;
        }

        return this;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items.
     *
     * @return A list of <a>BatchResultErrorEntry</a> items.
     */
    public java.util.List<BatchResultErrorEntry> getFailed() {
        if (failed == null) {
            failed = new ArrayList<BatchResultErrorEntry>();
        }
        return failed;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items.
     */
    public void setFailed(List<BatchResultErrorEntry> failed) {
        if (failed == null) {
            this.failed = null;
            return;
        }
        List<BatchResultErrorEntry> failedCopy = new ArrayList<BatchResultErrorEntry>(failed.size());
        failedCopy.addAll(failed);
        this.failed = failedCopy;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ChangeMessageVisibilityBatchResult withFailed(BatchResultErrorEntry... failed) {
        for (BatchResultErrorEntry value : failed) {
            getFailed().add(value);
        }
        return this;
    }

    /**
     * A list of <a>BatchResultErrorEntry</a> items.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param failed A list of <a>BatchResultErrorEntry</a> items.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ChangeMessageVisibilityBatchResult withFailed(java.util.Collection<BatchResultErrorEntry> failed) {
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
     * @see Object#toString()
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeMessageVisibilityBatchResult that = (ChangeMessageVisibilityBatchResult) o;

        if (!getSuccessful().equals(that.getSuccessful())) return false;
        return getFailed().equals(that.getFailed());

    }

    @Override
    public int hashCode() {
        int result = getSuccessful().hashCode();
        result = 31 * result + getFailed().hashCode();
        return result;
    }
}
    
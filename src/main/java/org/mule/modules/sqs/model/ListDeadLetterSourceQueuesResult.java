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
 * A list of your dead letter source queues.
 * </p>
 */
public class ListDeadLetterSourceQueuesResult implements Serializable {

    /**
     * A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     */
    private List<String> queueUrls;

    /**
     * A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     *
     * @return A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     */
    public java.util.List<String> getQueueUrls() {
        if (queueUrls == null) {
            queueUrls = new ArrayList<String>();
        }
        return queueUrls;
    }

    /**
     * A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     *
     * @param queueUrls A list of source queue URLs that have the RedrivePolicy queue
     *                  attribute configured with a dead letter queue.
     */
    public void setQueueUrls(Collection<String> queueUrls) {
        if (queueUrls == null) {
            this.queueUrls = null;
            return;
        }
        List<String> queueUrlsCopy = new ArrayList<String>(queueUrls.size());
        queueUrlsCopy.addAll(queueUrls);
        this.queueUrls = queueUrlsCopy;
    }

    /**
     * A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueUrls A list of source queue URLs that have the RedrivePolicy queue
     *                  attribute configured with a dead letter queue.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ListDeadLetterSourceQueuesResult withQueueUrls(String... queueUrls) {
        if (getQueueUrls() == null) setQueueUrls(new ArrayList<String>(queueUrls.length));
        for (String value : queueUrls) {
            getQueueUrls().add(value);
        }
        return this;
    }

    /**
     * A list of source queue URLs that have the RedrivePolicy queue
     * attribute configured with a dead letter queue.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueUrls A list of source queue URLs that have the RedrivePolicy queue
     *                  attribute configured with a dead letter queue.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public ListDeadLetterSourceQueuesResult withQueueUrls(Collection<String> queueUrls) {
        if (queueUrls == null) {
            this.queueUrls = null;
        } else {
            List<String> queueUrlsCopy = new ArrayList<String>(queueUrls.size());
            queueUrlsCopy.addAll(queueUrls);
            this.queueUrls = queueUrlsCopy;
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
        if (getQueueUrls() != null) sb.append("QueueUrls: " + getQueueUrls());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getQueueUrls() == null) ? 0 : getQueueUrls().hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof ListDeadLetterSourceQueuesResult == false) return false;
        ListDeadLetterSourceQueuesResult other = (ListDeadLetterSourceQueuesResult) obj;

        if (other.getQueueUrls() == null ^ this.getQueueUrls() == null) return false;
        if (other.getQueueUrls() != null && other.getQueueUrls().equals(this.getQueueUrls()) == false) return false;
        return true;
    }

}
    
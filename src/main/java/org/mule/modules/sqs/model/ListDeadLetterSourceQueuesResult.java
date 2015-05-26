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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListDeadLetterSourceQueuesResult that = (ListDeadLetterSourceQueuesResult) o;

        return getQueueUrls().equals(that.getQueueUrls());

    }

    @Override
    public int hashCode() {
        return getQueueUrls().hashCode();
    }
}
    
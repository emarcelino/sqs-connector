/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

/**
 * <p>
 * Encloses the id an entry in DeleteMessageBatch.
 * </p>
 */
public class DeleteMessageBatchResultEntry implements Serializable {

    /**
     * Represents a successfully deleted message.
     */
    private String id;

    /**
     * Represents a successfully deleted message.
     *
     * @return Represents a successfully deleted message.
     */
    public String getId() {
        return id;
    }

    /**
     * Represents a successfully deleted message.
     *
     * @param id Represents a successfully deleted message.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Represents a successfully deleted message.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id Represents a successfully deleted message.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public DeleteMessageBatchResultEntry withId(String id) {
        this.id = id;
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
        if (getId() != null) sb.append("Id: " + getId());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getId() == null) ? 0 : getId().hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof DeleteMessageBatchResultEntry == false) return false;
        DeleteMessageBatchResultEntry other = (DeleteMessageBatchResultEntry) obj;

        if (other.getId() == null ^ this.getId() == null) return false;
        if (other.getId() != null && other.getId().equals(this.getId()) == false) return false;
        return true;
    }

}
    
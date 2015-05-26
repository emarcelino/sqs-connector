/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * A list of returned queue attributes.
 * </p>
 */
public class GetQueueAttributesResult implements Serializable {

    /**
     * A map of attributes to the respective values.
     */
    private Map<String, String> attributes;

    /**
     * A map of attributes to the respective values.
     *
     * @return A map of attributes to the respective values.
     */
    public Map<String, String> getAttributes() {

        if (attributes == null) {
            attributes = new HashMap<String, String>();
        }
        return attributes;
    }

    /**
     * A map of attributes to the respective values.
     *
     * @param attributes A map of attributes to the respective values.
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * A map of attributes to the respective values.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributes A map of attributes to the respective values.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public GetQueueAttributesResult withAttributes(Map<String, String> attributes) {
        setAttributes(attributes);
        return this;
    }

    /**
     * A map of attributes to the respective values.
     * <p/>
     * The method adds a new key-value pair into Attributes parameter, and
     * returns a reference to this object so that method calls can be chained
     * together.
     *
     * @param key   The key of the entry to be added into Attributes.
     * @param value The corresponding value of the entry to be added into Attributes.
     */
    public GetQueueAttributesResult addAttributesEntry(String key, String value) {
        if (null == this.attributes) {
            this.attributes = new HashMap<String, String>();
        }
        if (this.attributes.containsKey(key))
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        this.attributes.put(key, value);
        return this;
    }

    /**
     * Removes all the entries added into Attributes.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     */
    public GetQueueAttributesResult clearAttributesEntries() {
        this.attributes = null;
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
        if (getAttributes() != null) sb.append("Attributes: " + getAttributes());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetQueueAttributesResult that = (GetQueueAttributesResult) o;

        return getAttributes().equals(that.getAttributes());

    }

    @Override
    public int hashCode() {
        return getAttributes().hashCode();
    }
}
    
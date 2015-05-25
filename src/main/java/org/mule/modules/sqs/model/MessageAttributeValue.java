/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MessageAttributeValue implements Serializable {

    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     */
    private String stringValue;

    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     */
    private java.nio.ByteBuffer binaryValue;

    /**
     * Not implemented. Reserved for future use.
     */
    private List<String> stringListValues;

    /**
     * Not implemented. Reserved for future use.
     */
    private List<ByteBuffer> binaryListValues;

    /**
     * Amazon SQS supports the following logical data types: String, Number,
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
     */
    private String dataType;

    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *
     * @return Strings are Unicode with UTF8 binary encoding. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     */
    public String getStringValue() {
        return stringValue;
    }
    
    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *
     * @param stringValue Strings are Unicode with UTF8 binary encoding. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    
    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param stringValue Strings are Unicode with UTF8 binary encoding. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withStringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     *
     * @return Binary type attributes can store any binary data, for example,
     *         compressed data, encrypted data, or images.
     */
    public java.nio.ByteBuffer getBinaryValue() {
        return binaryValue;
    }
    
    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     *
     * @param binaryValue Binary type attributes can store any binary data, for example,
     *         compressed data, encrypted data, or images.
     */
    public void setBinaryValue(java.nio.ByteBuffer binaryValue) {
        this.binaryValue = binaryValue;
    }
    
    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param binaryValue Binary type attributes can store any binary data, for example,
     *         compressed data, encrypted data, or images.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withBinaryValue(java.nio.ByteBuffer binaryValue) {
        this.binaryValue = binaryValue;
        return this;
    }

    /**
     * Not implemented. Reserved for future use.
     *
     * @return Not implemented. Reserved for future use.
     */
    public java.util.List<String> getStringListValues() {
        if (stringListValues == null) {
              stringListValues = new ArrayList<String>();
        }
        return stringListValues;
    }
    
    /**
     * Not implemented. Reserved for future use.
     *
     * @param stringListValues Not implemented. Reserved for future use.
     */
    public void setStringListValues(java.util.Collection<String> stringListValues) {
        if (stringListValues == null) {
            this.stringListValues = null;
            return;
        }
        List<String> stringListValuesCopy = new ArrayList<String>(stringListValues.size());
        stringListValuesCopy.addAll(stringListValues);
        this.stringListValues = stringListValuesCopy;
    }
    
    /**
     * Not implemented. Reserved for future use.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param stringListValues Not implemented. Reserved for future use.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withStringListValues(String... stringListValues) {
        if (getStringListValues() == null) setStringListValues(new java.util.ArrayList<String>(stringListValues.length));
        for (String value : stringListValues) {
            getStringListValues().add(value);
        }
        return this;
    }
    
    /**
     * Not implemented. Reserved for future use.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param stringListValues Not implemented. Reserved for future use.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withStringListValues(java.util.Collection<String> stringListValues) {
        if (stringListValues == null) {
            this.stringListValues = null;
        } else {
            List<String> stringListValuesCopy = new ArrayList<String>(stringListValues.size());
            stringListValuesCopy.addAll(stringListValues);
            this.stringListValues = stringListValuesCopy;
        }

        return this;
    }

    /**
     * Not implemented. Reserved for future use.
     *
     * @return Not implemented. Reserved for future use.
     */
    public java.util.List<java.nio.ByteBuffer> getBinaryListValues() {
        if (binaryListValues == null) {
              binaryListValues = new ArrayList<ByteBuffer>();
        }
        return binaryListValues;
    }
    
    /**
     * Not implemented. Reserved for future use.
     *
     * @param binaryListValues Not implemented. Reserved for future use.
     */
    public void setBinaryListValues(java.util.Collection<java.nio.ByteBuffer> binaryListValues) {
        if (binaryListValues == null) {
            this.binaryListValues = null;
            return;
        }
        List<ByteBuffer> binaryListValuesCopy = new ArrayList<ByteBuffer>(binaryListValues.size());
        binaryListValuesCopy.addAll(binaryListValues);
        this.binaryListValues = binaryListValuesCopy;
    }
    
    /**
     * Not implemented. Reserved for future use.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param binaryListValues Not implemented. Reserved for future use.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withBinaryListValues(java.nio.ByteBuffer... binaryListValues) {
        if (getBinaryListValues() == null) setBinaryListValues(new java.util.ArrayList<java.nio.ByteBuffer>(binaryListValues.length));
        for (java.nio.ByteBuffer value : binaryListValues) {
            getBinaryListValues().add(value);
        }
        return this;
    }
    
    /**
     * Not implemented. Reserved for future use.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param binaryListValues Not implemented. Reserved for future use.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withBinaryListValues(java.util.Collection<java.nio.ByteBuffer> binaryListValues) {
        if (binaryListValues == null) {
            this.binaryListValues = null;
        } else {
            List<ByteBuffer> binaryListValuesCopy = new ArrayList<ByteBuffer>(binaryListValues.size());
            binaryListValuesCopy.addAll(binaryListValues);
            this.binaryListValues = binaryListValuesCopy;
        }

        return this;
    }

    /**
     * Amazon SQS supports the following logical data types: String, Number,
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
     *
     * @return Amazon SQS supports the following logical data types: String, Number,
     *         and Binary. In addition, you can append your own custom labels. For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     *         Attribute Data Types</a>.
     */
    public String getDataType() {
        return dataType;
    }
    
    /**
     * Amazon SQS supports the following logical data types: String, Number,
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
     *
     * @param dataType Amazon SQS supports the following logical data types: String, Number,
     *         and Binary. In addition, you can append your own custom labels. For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     *         Attribute Data Types</a>.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    /**
     * Amazon SQS supports the following logical data types: String, Number,
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dataType Amazon SQS supports the following logical data types: String, Number,
     *         and Binary. In addition, you can append your own custom labels. For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     *         Attribute Data Types</a>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public MessageAttributeValue withDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStringValue() != null) sb.append("StringValue: " + getStringValue() + ",");
        if (getBinaryValue() != null) sb.append("BinaryValue: " + getBinaryValue() + ",");
        if (getStringListValues() != null) sb.append("StringListValues: " + getStringListValues() + ",");
        if (getBinaryListValues() != null) sb.append("BinaryListValues: " + getBinaryListValues() + ",");
        if (getDataType() != null) sb.append("DataType: " + getDataType() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getStringValue() == null) ? 0 : getStringValue().hashCode()); 
        hashCode = prime * hashCode + ((getBinaryValue() == null) ? 0 : getBinaryValue().hashCode()); 
        hashCode = prime * hashCode + ((getStringListValues() == null) ? 0 : getStringListValues().hashCode()); 
        hashCode = prime * hashCode + ((getBinaryListValues() == null) ? 0 : getBinaryListValues().hashCode()); 
        hashCode = prime * hashCode + ((getDataType() == null) ? 0 : getDataType().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof MessageAttributeValue == false) return false;
        MessageAttributeValue other = (MessageAttributeValue)obj;
        
        if (other.getStringValue() == null ^ this.getStringValue() == null) return false;
        if (other.getStringValue() != null && other.getStringValue().equals(this.getStringValue()) == false) return false; 
        if (other.getBinaryValue() == null ^ this.getBinaryValue() == null) return false;
        if (other.getBinaryValue() != null && other.getBinaryValue().equals(this.getBinaryValue()) == false) return false; 
        if (other.getStringListValues() == null ^ this.getStringListValues() == null) return false;
        if (other.getStringListValues() != null && other.getStringListValues().equals(this.getStringListValues()) == false) return false; 
        if (other.getBinaryListValues() == null ^ this.getBinaryListValues() == null) return false;
        if (other.getBinaryListValues() != null && other.getBinaryListValues().equals(this.getBinaryListValues()) == false) return false; 
        if (other.getDataType() == null ^ this.getDataType() == null) return false;
        if (other.getDataType() != null && other.getDataType().equals(this.getDataType()) == false) return false; 
        return true;
    }
    
}
    
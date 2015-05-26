/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.model;

import java.io.Serializable;

public class MessageAttributeValue implements Serializable {

    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     */
    private String stringValue = null;

    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     */
    private java.nio.ByteBuffer binaryValue = null;

    /**
     * Amazon SQS supports the following logical data types: String, Number,
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
     */
    private String dataType = null;

    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *
     * @return Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
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
     *                    values, see <a
     *                    href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Strings are Unicode with UTF8 binary encoding. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param stringValue Strings are Unicode with UTF8 binary encoding. For a list of code
     *                    values, see <a
     *                    href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * @return A reference to this updated object so that method calls can be chained
     * together.
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
     * compressed data, encrypted data, or images.
     */
    public java.nio.ByteBuffer getBinaryValue() {
        return binaryValue;
    }

    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     *
     * @param binaryValue Binary type attributes can store any binary data, for example,
     *                    compressed data, encrypted data, or images.
     */
    public void setBinaryValue(java.nio.ByteBuffer binaryValue) {
        this.binaryValue = binaryValue;
    }

    /**
     * Binary type attributes can store any binary data, for example,
     * compressed data, encrypted data, or images.
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param binaryValue Binary type attributes can store any binary data, for example,
     *                    compressed data, encrypted data, or images.
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public MessageAttributeValue withBinaryValue(java.nio.ByteBuffer binaryValue) {
        this.binaryValue = binaryValue;
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
     * and Binary. In addition, you can append your own custom labels. For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     * Attribute Data Types</a>.
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
     *                 and Binary. In addition, you can append your own custom labels. For
     *                 more information, see <a
     *                 href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     *                 Attribute Data Types</a>.
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
     * <p/>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dataType Amazon SQS supports the following logical data types: String, Number,
     *                 and Binary. In addition, you can append your own custom labels. For
     *                 more information, see <a
     *                 href="http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/SQSMessageAttributes.html#SQSMessageAttributes.DataTypes">Message
     *                 Attribute Data Types</a>.
     * @return A reference to this updated object so that method calls can be chained
     * together.
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
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStringValue() != null) sb.append("StringValue: " + getStringValue() + ",");
        if (getBinaryValue() != null) sb.append("BinaryValue: " + getBinaryValue() + ",");
        if (getDataType() != null) sb.append("DataType: " + getDataType());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageAttributeValue that = (MessageAttributeValue) o;

        if (!getStringValue().equals(that.getStringValue())) return false;
        if (!getBinaryValue().equals(that.getBinaryValue())) return false;
        return getDataType().equals(that.getDataType());

    }

    @Override
    public int hashCode() {
        int result = getStringValue().hashCode();
        result = 31 * result + getBinaryValue().hashCode();
        result = 31 * result + getDataType().hashCode();
        return result;
    }
}
    
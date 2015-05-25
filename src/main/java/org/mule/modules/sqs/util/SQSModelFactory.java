/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.util;

import com.amazonaws.services.sqs.model.*;
import org.mule.modules.sqs.model.ChangeMessageVisibilityBatchResult;
import org.mule.modules.sqs.model.DeleteMessageBatchResult;
import org.mule.modules.sqs.model.SendMessageBatchResult;
import org.mule.modules.sqs.model.SendMessageResult;

import java.util.*;

public class SQSModelFactory {

    public static ChangeMessageVisibilityBatchResult getChangeMessageVisibilityBatchResult(List<ChangeMessageVisibilityBatchResultEntry> successful, List<BatchResultErrorEntry> failed) {
        ChangeMessageVisibilityBatchResult batchResult = new ChangeMessageVisibilityBatchResult();
        if (successful != null && !successful.isEmpty()) {
            List<org.mule.modules.sqs.model.ChangeMessageVisibilityBatchResultEntry> visibilityEntries = new ArrayList<org.mule.modules.sqs.model.ChangeMessageVisibilityBatchResultEntry>(successful.size());
            Iterator<ChangeMessageVisibilityBatchResultEntry> iterator = successful.iterator();
            while (iterator.hasNext()) {
                visibilityEntries.add(new org.mule.modules.sqs.model.ChangeMessageVisibilityBatchResultEntry().withId(iterator.next().getId()));
            }
            batchResult.setSuccessful(visibilityEntries);
        }

        if (failed != null && !failed.isEmpty()) {
            List<org.mule.modules.sqs.model.BatchResultErrorEntry> errorEntries = new ArrayList<org.mule.modules.sqs.model.BatchResultErrorEntry>(failed.size());
            Iterator<BatchResultErrorEntry> iterator = failed.iterator();
            while (iterator.hasNext()) {
                BatchResultErrorEntry errorEntry = iterator.next();

                org.mule.modules.sqs.model.BatchResultErrorEntry entry = new org.mule.modules.sqs.model.BatchResultErrorEntry();
                entry.setId(errorEntry.getId());
                entry.setCode(errorEntry.getCode());
                entry.setMessage(errorEntry.getMessage());
                entry.setSenderFault(errorEntry.getSenderFault());

                errorEntries.add(entry);
            }
            batchResult.setFailed(errorEntries);
        }

        return batchResult;
    }

    public static List<ChangeMessageVisibilityBatchRequestEntry> getChangeMessageVisibilityBatchRequestEntries(List<org.mule.modules.sqs.model.ChangeMessageVisibilityBatchRequestEntry> receiptHandles) {
        List<ChangeMessageVisibilityBatchRequestEntry> requestEntries = new ArrayList<ChangeMessageVisibilityBatchRequestEntry>();
        if (receiptHandles != null && !receiptHandles.isEmpty()) {
            Iterator<org.mule.modules.sqs.model.ChangeMessageVisibilityBatchRequestEntry> iterator = receiptHandles.iterator();
            while (iterator.hasNext()) {
                org.mule.modules.sqs.model.ChangeMessageVisibilityBatchRequestEntry requestEntry = iterator.next();

                ChangeMessageVisibilityBatchRequestEntry entry = new ChangeMessageVisibilityBatchRequestEntry();
                entry.setId(requestEntry.getId());
                entry.setReceiptHandle(requestEntry.getReceiptHandle());
                entry.setVisibilityTimeout(requestEntry.getVisibilityTimeout());

                requestEntries.add(entry);
            }
        }
        return requestEntries;
    }

    public static DeleteMessageBatchResult getDeleteMessageBatchResult(List<DeleteMessageBatchResultEntry> successful, List<BatchResultErrorEntry> failed) {
        DeleteMessageBatchResult batchResult = new DeleteMessageBatchResult();
        if (successful != null && !successful.isEmpty()) {
            List<org.mule.modules.sqs.model.DeleteMessageBatchResultEntry> visibilityEntries = new ArrayList<org.mule.modules.sqs.model.DeleteMessageBatchResultEntry>(successful.size());
            Iterator<DeleteMessageBatchResultEntry> iterator = successful.iterator();
            while (iterator.hasNext()) {
                visibilityEntries.add(new org.mule.modules.sqs.model.DeleteMessageBatchResultEntry().withId(iterator.next().getId()));
            }
            batchResult.setSuccessful(visibilityEntries);
        }

        if (failed != null && !failed.isEmpty()) {
            List<org.mule.modules.sqs.model.BatchResultErrorEntry> errorEntries = new ArrayList<org.mule.modules.sqs.model.BatchResultErrorEntry>(failed.size());
            Iterator<BatchResultErrorEntry> iterator = failed.iterator();
            while (iterator.hasNext()) {
                BatchResultErrorEntry errorEntry = iterator.next();

                org.mule.modules.sqs.model.BatchResultErrorEntry entry = new org.mule.modules.sqs.model.BatchResultErrorEntry();
                entry.setId(errorEntry.getId());
                entry.setCode(errorEntry.getCode());
                entry.setMessage(errorEntry.getMessage());
                entry.setSenderFault(errorEntry.getSenderFault());

                errorEntries.add(entry);
            }
            batchResult.setFailed(errorEntries);
        }

        return batchResult;
    }

    public static List<DeleteMessageBatchRequestEntry> getDeleteMessageBatchRequestEntries(List<org.mule.modules.sqs.model.DeleteMessageBatchRequestEntry> receiptHandles) {
        List<DeleteMessageBatchRequestEntry> requestEntries = new ArrayList<DeleteMessageBatchRequestEntry>();
        if (receiptHandles != null && !receiptHandles.isEmpty()) {
            Iterator<org.mule.modules.sqs.model.DeleteMessageBatchRequestEntry> iterator = receiptHandles.iterator();
            while (iterator.hasNext()) {
                org.mule.modules.sqs.model.DeleteMessageBatchRequestEntry requestEntry = iterator.next();

                DeleteMessageBatchRequestEntry entry = new DeleteMessageBatchRequestEntry();
                entry.setId(requestEntry.getId());
                entry.setReceiptHandle(requestEntry.getReceiptHandle());

                requestEntries.add(entry);
            }
        }
        return requestEntries;
    }

    public static Map<String, MessageAttributeValue> getMessageAttributes(Map<String, Object> messageAttributes) {
        Map<String, MessageAttributeValue> attributes = new HashMap<String, MessageAttributeValue>();
        if (messageAttributes != null && !messageAttributes.isEmpty()) {
            for (Map.Entry<String, Object> entry : messageAttributes.entrySet()) {
                org.mule.modules.sqs.model.MessageAttributeValue attributeValue = (org.mule.modules.sqs.model.MessageAttributeValue) entry.getValue();

                MessageAttributeValue value = new MessageAttributeValue();
                value.setBinaryListValues(attributeValue.getBinaryListValues());
                value.setBinaryValue(attributeValue.getBinaryValue());
                value.setDataType(attributeValue.getDataType());
                value.setStringListValues(attributeValue.getStringListValues());
                value.setStringValue(attributeValue.getStringValue());

                attributes.put(entry.getKey(), value);
            }
        }
        return attributes;
    }

    private static Map<String, MessageAttributeValue> getAttributes(Map<String, org.mule.modules.sqs.model.MessageAttributeValue> messageAttributes) {
        Map<String, MessageAttributeValue> attributes = new HashMap<String, MessageAttributeValue>();
        if (messageAttributes != null && !messageAttributes.isEmpty()) {
            for (Map.Entry<String, org.mule.modules.sqs.model.MessageAttributeValue> entry : messageAttributes.entrySet()) {
                org.mule.modules.sqs.model.MessageAttributeValue attributeValue = entry.getValue();

                MessageAttributeValue value = new MessageAttributeValue();
                value.setBinaryListValues(attributeValue.getBinaryListValues());
                value.setBinaryValue(attributeValue.getBinaryValue());
                value.setDataType(attributeValue.getDataType());
                value.setStringListValues(attributeValue.getStringListValues());
                value.setStringValue(attributeValue.getStringValue());

                attributes.put(entry.getKey(), value);
            }
        }
        return attributes;
    }

    public static SendMessageResult getSendMessageResult(String messageId, String md5OfMessageBody, String md5OfMessageAttributes) {
        SendMessageResult messageResult = new SendMessageResult();
        messageResult.setMessageId(messageId);
        messageResult.setMD5OfMessageBody(md5OfMessageBody);
        messageResult.setMD5OfMessageAttributes(md5OfMessageAttributes);
        return messageResult;
    }

    public static List<SendMessageBatchRequestEntry> getSendMessageBatchRequestEntries(List<org.mule.modules.sqs.model.SendMessageBatchRequestEntry> messages) {
        List<SendMessageBatchRequestEntry> requestEntries = new ArrayList<SendMessageBatchRequestEntry>();
        if (messages != null && !messages.isEmpty()) {
            Iterator<org.mule.modules.sqs.model.SendMessageBatchRequestEntry> iterator = messages.iterator();
            while (iterator.hasNext()) {
                org.mule.modules.sqs.model.SendMessageBatchRequestEntry requestEntry = iterator.next();

                SendMessageBatchRequestEntry entry = new SendMessageBatchRequestEntry();
                entry.setId(requestEntry.getId());
                entry.setMessageBody(requestEntry.getMessageBody());
                entry.setDelaySeconds(requestEntry.getDelaySeconds());
                entry.setMessageAttributes(getAttributes(requestEntry.getMessageAttributes()));
                requestEntries.add(entry);
            }
        }
        return requestEntries;
    }


    public static SendMessageBatchResult getSendMessageBatchResult(List<SendMessageBatchResultEntry> successful, List<BatchResultErrorEntry> failed) {
        SendMessageBatchResult batchResult = new SendMessageBatchResult();
        if (successful != null && !successful.isEmpty()) {
            List<org.mule.modules.sqs.model.SendMessageBatchResultEntry> visibilityEntries = new ArrayList<org.mule.modules.sqs.model.SendMessageBatchResultEntry>(successful.size());
            Iterator<SendMessageBatchResultEntry> iterator = successful.iterator();
            while (iterator.hasNext()) {
                SendMessageBatchResultEntry requestEntry = iterator.next();

                org.mule.modules.sqs.model.SendMessageBatchResultEntry entry = new org.mule.modules.sqs.model.SendMessageBatchResultEntry();
                entry.setId(requestEntry.getId());
                entry.setMessageId(requestEntry.getMessageId());
                entry.setMD5OfMessageBody(requestEntry.getMD5OfMessageBody());
                entry.setMD5OfMessageAttributes(requestEntry.getMD5OfMessageAttributes());
                visibilityEntries.add(entry);
            }
            batchResult.setSuccessful(visibilityEntries);
        }

        if (failed != null && !failed.isEmpty()) {
            List<org.mule.modules.sqs.model.BatchResultErrorEntry> errorEntries = new ArrayList<org.mule.modules.sqs.model.BatchResultErrorEntry>(failed.size());
            Iterator<BatchResultErrorEntry> iterator = failed.iterator();
            while (iterator.hasNext()) {
                BatchResultErrorEntry errorEntry = iterator.next();

                org.mule.modules.sqs.model.BatchResultErrorEntry entry = new org.mule.modules.sqs.model.BatchResultErrorEntry();
                entry.setId(errorEntry.getId());
                entry.setCode(errorEntry.getCode());
                entry.setMessage(errorEntry.getMessage());
                entry.setSenderFault(errorEntry.getSenderFault());

                errorEntries.add(entry);
            }
            batchResult.setFailed(errorEntries);
        }

        return batchResult;
    }
}

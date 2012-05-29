/**
 * Mule Amazon Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.amazon;

import static org.junit.Assert.*;

import java.util.Map;

import javax.xml.ws.Holder;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.api.callback.SourceCallback;

import com.xerox.amazonws.sqs2.SQSException;

/**
 * @author Gaston Ponti
 * @since Nov 24, 2011
 */

public class SQSRemoveMessageTestDriver
{
    private class InterruptCallback implements SourceCallback
    {
        @Override
        public Object process(Object payload, Map<String, Object> properties) throws Exception
        {
            interrupt();
            return null;
        }

        @Override
        public Object process(Object payload) throws Exception
        {
            interrupt();
            return null;

        }

        @Override
        public Object process() throws Exception
        {
            interrupt();
            return null;
        }

        private void interrupt()
        {
            Thread.currentThread().interrupt();
        }
    }

    private static SQSConnector module;

    @BeforeClass
    public static void init() throws ConnectionException, SQSException {
        module = new SQSConnector();
        module.setAccessKey(System.getenv("sqsAccessKey"));
        module.setSecretAccessKey(System.getenv("sqsSecretAccessKey"));
        module.connect("test5613809");
        assertTrue(module.getApproximateNumberOfMessages()==0);
    }

    @Before
    public void addOneMessage() throws SQSException {
        module.sendMessage("Hello");
    }

    @Test
    public void retrieveMessageWithPreserveMessagesFlagTrue() throws SQSException {
        final Holder<String> id = new Holder<String>();

        SourceCallback callback = new InterruptCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception
            {
                id.value = (String) properties.get("sqs.message.receipt.handle");
                return super.process(payload, properties);
            }
        };
        module.receiveMessages(callback, 0, true, 1000L, 1);

        assertTrue(module.getApproximateNumberOfMessages()!=0);

        module.deleteMessage(id.value);

        assertTrue(module.getApproximateNumberOfMessages()==0);
    }

    @Test
    public void retrieveMessageWithPreserveMessagesFlagFalse() throws SQSException {

        SourceCallback callback = new InterruptCallback();
        module.receiveMessages(callback, 0, false, 1000L, 1);

        assertTrue(module.getApproximateNumberOfMessages()==0);
    }
}

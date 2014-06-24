/**
 * Mule Amazon SQS Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sqs;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.callback.SourceCallback;

import javax.xml.ws.Holder;
import java.util.Map;

import static org.junit.Assert.assertTrue;

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
        public MuleEvent processEvent(MuleEvent muleEvent) throws MuleException {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public static void init() throws ConnectionException {
        module = new SQSConnector();
        module.connect(System.getenv("sqsAccessKey"), System.getenv("sqsSecretKey"), "test5613809");
        assertTrue(module.getApproximateNumberOfMessages(null)==0);
    }

    @Before
    public void addOneMessage() {
        module.sendMessage("Hello", null);
    }

    @Test
    public void retrieveMessageWithPreserveMessagesFlagTrue() {
        final Holder<String> id = new Holder<String>();

        SourceCallback callback = new InterruptCallback() {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception
            {
                id.value = (String) properties.get("sqs.message.receipt.handle");
                return super.process(payload, properties);
            }
        };
        module.receiveMessages(callback, 0, true, null, 1, null);

        assertTrue(module.getApproximateNumberOfMessages(null)!=0);

        module.deleteMessage(id.value, null);

        assertTrue(module.getApproximateNumberOfMessages(null)==0);
    }

    @Test
    public void retrieveMessageWithPreserveMessagesFlagFalse() {

        SourceCallback callback = new InterruptCallback();
        module.receiveMessages(callback, 0, false, null, 1, null);

        assertTrue(module.getApproximateNumberOfMessages(null)==0);
    }
}

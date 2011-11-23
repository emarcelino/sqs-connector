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

import java.util.Map;

import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.api.callback.SourceCallback;

import com.xerox.amazonws.sqs2.SQSException;

/**
 * @author Gaston Ponti
 * @since Nov 22, 2011
 */

public class SQSConnectorTestDriver
{
    private SQSConnector sqsConnector;
    
    @Before
    public void setup() throws ConnectionException
    {
        sqsConnector = new SQSConnector();
        sqsConnector.setAccessKey(System.getenv("sqsAccessKey"));
        sqsConnector.setSecretAccessKey(System.getenv("sqsSecretAccessKey"));
        sqsConnector.connect(System.getenv("sqsQueueName"));
        
    }
    
    /**
     * This sends one generic message, to test the recieveMessage processor retries.
     * For this, it sends interrupt signal to the thread, because recieveMessage, will
     * keep pulling messages until the thread where is running has been interrupted.
     * 
     * So, the quantity of errors logged, will be 1+retries. The same number need it to
     * stop the main thread in the SourceCallback. If the number in the SourceCallback
     * is more than 1+retries, the thread will never stop.
     * 
     * Thus, if the test ends, it means that is working.
     * 
     * @throws SQSException
     */
    @Test
    public void queueRetriesTest() throws SQSException
    {
        final MutableInt timesRun = new MutableInt(0);
        sqsConnector.sendMessage("Test Message");
        
        SourceCallback callback =  new SourceCallback()
        {
            
            @Override
            public Object process(Object arg0, Map<String, Object> arg1) throws Exception
            {
                timesRun.increment();
                if(timesRun.intValue() == 3)
                    Thread.currentThread().interrupt();
                throw new Exception();
            }
            
            @Override
            public Object process(Object arg0) throws Exception
            {
                throw new Exception();
            }
        };
        
        sqsConnector.receiveMessages(callback, 2);
    }
}

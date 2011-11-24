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
        
        SourceCallback callback = new SourceCallback()
        {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception
            {
                id.value = (String) properties.get("sqs.message.receipt.handle");
                Thread.currentThread().interrupt();
                return null;
            }
            
            @Override
            public Object process(Object payload) throws Exception
            {
                Thread.currentThread().interrupt();
                return null;
                
            }
        };
        module.receiveMessages(callback, 0, true);
        
        assertTrue(module.getApproximateNumberOfMessages()!=0);
        
        module.deleteMessage(id.value);
        
        assertTrue(module.getApproximateNumberOfMessages()==0);
    }
    
    @Test
    public void retrieveMessageWithPreserveMessagesFlagFalse() throws SQSException {
        
        SourceCallback callback = new SourceCallback()
        {
            @Override
            public Object process(Object payload, Map<String, Object> properties) throws Exception
            {
                Thread.currentThread().interrupt();
                return null;
            }
            
            @Override
            public Object process(Object payload) throws Exception
            {
                Thread.currentThread().interrupt();
                return null;
                
            }
        };
        module.receiveMessages(callback, 0, false);
        
        assertTrue(module.getApproximateNumberOfMessages()==0);
    }
}

/**
 * Mule Amazon Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesoft.demo.amazon;

import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.transport.PropertyScope;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;


/**
 * @author Gaston Ponti
 * @since Nov 16, 2011
 */

public class AmazonFunctionalTestDriver extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }
    
    @Test
    public void testUsingTwitterStatus() throws Exception
    {
        MuleEvent process = lookupFlowConstruct("producer").process(getTestEvent(""));
        System.out.println(process.getMessage().getPayload());
    }
    
    @Test
    public void testPutSomeStatusInMongo() throws Exception
    {
        MuleEvent event = getTestEvent("");
        event.getMessage().setProperty("status", "Hello world", PropertyScope.INBOUND);
        MuleEvent process = lookupFlowConstruct("producerEchoInMongo").process(event);
        System.out.println(process.getMessage().getPayload());
    }

    private Flow lookupFlowConstruct(final String name)
    {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}


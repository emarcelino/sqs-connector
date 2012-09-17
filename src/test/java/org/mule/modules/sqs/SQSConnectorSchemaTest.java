/**
 * Mule SQS Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package org.mule.modules.sqs;

import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Ignore;
import org.junit.Test;

public class SQSConnectorSchemaTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "sqs-schema-test.xml";
    }

    @Ignore("FIXME - This test never worked!")
    @Test
    public void testCanParseXmlWithoutSchemaValidationErrors() throws Exception
    {
        // Nothing. Will fail if can not parse
    }
}

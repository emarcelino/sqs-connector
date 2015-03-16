/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules.sqs;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;

public class SQSConnectorTest extends FunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "sqs.xml";
    }

    // TODO: Unit Test each Connector method.

    @Ignore("Rewrite this test! It was trying to hit an actual Amazon instance.")
    @Test
    public void testSendMessage() throws Exception {
        Assert.assertTrue(true);
    }
}

/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs.automation;

import org.bouncycastle.util.encoders.Hex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mule.modules.sqs.SQSConnector;
import org.mule.modules.tests.ConnectorTestUtils;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;
import org.mule.tools.devkit.ctf.platform.PlatformManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SQSFunctionalTestParent {

    private static final Logger logger = LoggerFactory.getLogger(SQSFunctionalTestParent.class);

    public static final String TEST_QUEUE_REGION = "USEAST1";

    private SQSConnector connector;
    private ConnectorDispatcher<SQSConnector> dispatcher;

    @Before
    public void init() throws Exception {
        ConnectorTestContext<SQSConnector> context = ConnectorTestContext.getInstance(SQSConnector.class);
        dispatcher = context.getConnectorDispatcher();
        connector = dispatcher.createMockup();
    }

    protected SQSConnector getConnector() {
        return connector;
    }

    protected ConnectorDispatcher<SQSConnector> getDispatcher() {
        return dispatcher;
    }

    protected String md5(String string) throws NoSuchAlgorithmException {
        return new String(Hex.encode(MessageDigest.getInstance("MD5").digest(string.getBytes())));
    }
}

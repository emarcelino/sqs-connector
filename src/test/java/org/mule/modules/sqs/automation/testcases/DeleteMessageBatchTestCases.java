package org.mule.modules.sqs.automation.testcases;

import org.junit.Before;
import org.mule.modules.sqs.automation.SqsTestParent;

public class DeleteMessageBatchTestCases extends SqsTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteMessageBatchTestData");
    }
}

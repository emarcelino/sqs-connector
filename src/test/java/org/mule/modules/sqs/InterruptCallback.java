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

import java.util.Map;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.callback.SourceCallback;

abstract class InterruptCallback implements SourceCallback {
      @Override
      public Object process(Object payload, Map<String, Object> properties) throws Exception {
         interrupt();
         return null;
      }

    @Override
    public MuleEvent processEvent(MuleEvent muleEvent) throws MuleException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
      public Object process(Object payload) throws Exception {
         interrupt();
         return null;

      }

      @Override
      public Object process() throws Exception {
         interrupt();
         return null;
      }

      private void interrupt() {
         Thread.currentThread().interrupt();
      }
   }
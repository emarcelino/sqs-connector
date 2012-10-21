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

import java.io.Closeable;
import java.net.URI;

import org.jclouds.sqs.features.MessageApi;
import org.jclouds.sqs.features.QueueApi;

/**
 * Utility to connect to SQS
 *
 */
public interface SQSConnection extends Closeable {

   public static interface Builder {
      Builder region(String region);

      Builder credentials(String accessKey, String secretKey);

      SQSConnection build();
   }

   /**
    * returns true if the current user can perform a simple SQS command
    */
   boolean valid();

   QueueApi queueApi();

   MessageApi messageApiForQueue(URI queue);

   @Override
   void close();

}

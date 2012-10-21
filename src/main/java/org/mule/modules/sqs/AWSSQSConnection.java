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

import static com.google.common.base.Preconditions.checkNotNull;
import static org.jclouds.concurrent.MoreExecutors.sameThreadExecutor;

import java.net.URI;

import org.jclouds.ContextBuilder;
import org.jclouds.aws.sqs.AWSSQSProviderMetadata;
import org.jclouds.concurrent.config.ExecutorServiceModule;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.logging.Logger;
import org.jclouds.logging.log4j.config.Log4JLoggingModule;
import org.jclouds.rest.RestContext;
import org.jclouds.sqs.SQSApi;
import org.jclouds.sqs.SQSApiMetadata;
import org.jclouds.sqs.SQSAsyncApi;
import org.jclouds.sqs.features.MessageApi;
import org.jclouds.sqs.features.QueueApi;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

/**
 * An SQS connection that connects to amazon web services
 *
 */
public class AWSSQSConnection implements SQSConnection {

   public static Builder builder() {
      return new Builder();
   }

   public static class Builder implements SQSConnection.Builder {
      /**
       * configure jclouds to not use multithreading, and use log4j like mule does
       */
      private final static Iterable<Module> JCLOUDS_MODULES = ImmutableSet.<Module> builder()
              .add(new ExecutorServiceModule(sameThreadExecutor(), sameThreadExecutor()))
              .add(new Log4JLoggingModule())
              .build();

      private String region;
      private String accessKey;
      private String secretKey;

      @Override
      public SQSConnection.Builder region(@Nullable String region) {
         this.region = region;
         return this;
      }

      @Override
      public SQSConnection.Builder credentials(String accessKey, String secretKey) {
         this.accessKey = checkNotNull(accessKey, "accessKey");
         this.secretKey = checkNotNull(secretKey, "secretKey");
         return this;
      }

      @Override
      public SQSConnection build() {
         checkNotNull(accessKey, "accessKey");
         checkNotNull(secretKey, "secretKey");
         return new AWSSQSConnection(ContextBuilder.newBuilder(new AWSSQSProviderMetadata())
                                                   .credentials(accessKey, secretKey)
                                                   .modules(JCLOUDS_MODULES)
                                                   .build(SQSApiMetadata.CONTEXT_TOKEN), region);

      }

   }

   private final RestContext<SQSApi, SQSAsyncApi> context;
   private final Logger logger;
   @Nullable
   private final String region;

   protected AWSSQSConnection(RestContext<SQSApi, SQSAsyncApi> context, String region) {
      this.context = context;
      this.region = region;
      this.logger = context.utils().loggerFactory().getLogger(getClass().getName());
   }

   @Override
   public void close() {
      context.close();
   }

   @Override
   public boolean valid() {
      try {
         queueApi().list().get(0);
         return true;
      } catch (RuntimeException e) {
         logger.debug("connection to %s not valid: %s", context, e.getMessage());
         return false;
      }
   }

   @Override
   public QueueApi queueApi() {
      return context.getApi().getQueueApiForRegion(region);
   }

   @Override
   public MessageApi messageApiForQueue(URI queue) {
      return context.getApi().getMessageApiForQueue(checkNotNull(queue, "queue"));
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(context, region);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null || getClass() != obj.getClass())
         return false;
      AWSSQSConnection that = AWSSQSConnection.class.cast(obj);
      return Objects.equal(this.context, that.context) && Objects.equal(this.region, that.region);
   }

   @Override
   public String toString() {
      return Objects.toStringHelper(this).omitNullValues()
                                         .add("context", context)
                                         .add("region", region).toString();
   }
}

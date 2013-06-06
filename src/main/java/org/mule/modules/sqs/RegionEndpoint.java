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

/**
 * Enum for including Region endpoints
 *
 * @author Mulesoft, Inc
 */
public enum RegionEndpoint {

    USEAST1("http://sqs.us-east-1.amazonaws.com"),
    USWEST1("http://sqs.us-west-1.amazonaws.com"),
    USWEST2("http://sqs.us-west-2.amazonaws.com"),
    EUWEST1("http://sqs.eu-west-1.amazonaws.com"),
    APSOUTHEAST1("http://sqs.ap-southeast-1.amazonaws.com"),
    APSOUTHEAST2("http://sqs.ap-southeast-2.amazonaws.com"),
    APNORTHEAST1("http://sqs.ap-northeast-1.amazonaws.com"),
    SAEAST1("http://sqs.sa-east-1.amazonaws.com");

    private String value;

    RegionEndpoint(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }
}

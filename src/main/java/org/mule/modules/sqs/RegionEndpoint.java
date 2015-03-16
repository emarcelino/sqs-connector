/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sqs;

/**
 * Enum for including Region endpoints
 *
 * @author Mulesoft, Inc
 */
public enum RegionEndpoint {

    USEAST1("sqs.us-east-1.amazonaws.com"),
    USWEST1("sqs.us-west-1.amazonaws.com"),
    USWEST2("sqs.us-west-2.amazonaws.com"),
    EUWEST1("sqs.eu-west-1.amazonaws.com"),
    EUCENTRAL1("sqs.eu-central-1.amazonaws.com"),
    APSOUTHEAST1("sqs.ap-southeast-1.amazonaws.com"),
    APSOUTHEAST2("sqs.ap-southeast-2.amazonaws.com"),
    APNORTHEAST1("sqs.ap-northeast-1.amazonaws.com"),
    SAEAST1("sqs.sa-east-1.amazonaws.com");

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

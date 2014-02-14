
package org.mule.modules.sqs.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/sqs</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-14T11:49:58-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
public class SqsNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(SqsNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [sqs] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [sqs] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new SQSConnectorConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("send-message", new SendMessageDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("send-message", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-url", new GetUrlDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-url", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete-message", new DeleteMessageDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete-message", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete-queue", new DeleteQueueDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete-queue", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-queue-attributes", new GetQueueAttributesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-queue-attributes", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("set-queue-attribute", new SetQueueAttributeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("set-queue-attribute", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("add-permission", new AddPermissionDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("add-permission", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("remove-permission", new RemovePermissionDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("remove-permission", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-approximate-number-of-messages", new GetApproximateNumberOfMessagesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-approximate-number-of-messages", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("receive-messages", new ReceiveMessagesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("receive-messages", "@Source", ex);
        }
    }

}

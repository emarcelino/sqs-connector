**Mule Sample: Integrating with Amazon SQS**
======================================================

Purpose
=======

In this sample, I'll show how to build a simple application with Mule that sends a message to the queue, then registers that the message has been received.

Prerequisites
=============

In order to build and run this project you'll need
 

●     To have an Amazon AWS account and signed up for Amazon SQS [http://aws.amazon.com/sqs/](http://aws.amazon.com/sqs/)

●     To have downloaded and installed [Mule Studio Community edition](http://www.mulesoft.org/download-mule-esb-community-edition) on your computer

**Getting Mule Studio Ready**

If you haven't installed Mule Studio on your computer yet, it's time to download Mule Studio from this location: [http://www.mulesoft.org/download-mule-esb-community-edition](http://www.mulesoft.org/download-mule-esb-community-edition). You also have the option of downloading a 30 day trial of Mule Enterprise Edition from this location [http://www.mulesoft.com/mule-esb-enterprise](http://www.mulesoft.com/mule-esb-enterprise) if you want to evaluate and purchase the premium edition. This demo can be built using either community or enterprise edition. There is no specific installation that you need to run. Once you unzip the zip file to your desired location, you are ready to go. To install the Amazon SQS connector, you can download and install it from MuleStudio Cloud Connectors Update Site. To do that:

1. Open Mule Studio and from "Help" menu select "Install New Software...". Installation dialog box opens - Figure 4.

2. From "Work with" drop down, select "MuleStudio Cloud Connectors Update Site". The list of available connectors will be shown to you.

3. Find and select the Amazon SQS connector in the list of available connectors, the tree structure that is shown. A faster way to find a specific connector is to filter the list by typing the name of the connector in the input box above the list. You can choose more than one connector to be installed at once.

4. When you are done selecting the connectors to be installed, click on "Next" button. Details of each connector are shown on the next page. Click on "Next" button again and accept the terms of the license agreement.

5. Click on "Finish" button. The connector is downloaded and installed onto Studio. You'll need to restart the Studio for the installation to be completed.

![](images/image004.jpg)  
  
  
**Setting up the project**

Now that you've got your Mule Studio up and running, it's time to work on the Mule App. Create a new Mule Project by clicking on "File \> New \> Mule Project". In the new project dialog box, the only thing you are required to enter is the name of the project. You can click on "Next" to go through the rest of pages.

The first thing to do in your new app is to configure the connection to Amazon SQS. In the message flow editor, click on "Global Elements" tab on the bottom of the page. Then click on "Create" button on the top right of the tab. In the "Choose Global Element" type dialog box that opens select "Amazon SQS" under "Cloud Connectors" and click.

![](images/s3sqsCreateConfigRef.png)


In the Amazon SQS configuration dialog box that follows, you need to enter the following information. On the "General" tab, enter a name for your configuration settings such as "SQS-config". Also, put the "Access Key" and "Secret Key" from your Amazon AWS account in their respective fields. These values can be found [here](https://portal.aws.amazon.com/gp/aws/securityCredentials). If you have not accessed your Amazon AWS account remotely before, you will need to click **Create a new Access Key** in the Access Credentials box. You are done with the configuration. Click "Ok" to close the dialog box. 

![](images/sqsConfigPic.png)

The XML for the global element should look like this:  

    <sqs:config name="Amazon_SQS" accessKey="AKIAIA6DBXAES37G627A" secretKey="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" queueName="muletest" doc:name="Amazon SQS">
        <sqs:connection-pooling-profile initialisationPolicy="INITIALISE_ONE" exhaustedAction="WHEN_EXHAUSTED_GROW"/>
    </sqs:config>





**Building the flows**

It's time to build the flow that adds a message to the Amazon SQS queue now that we have our connection to Amazon SQS set up in Mule app. You will then create a second flow which logs that the message has been added to the queue - Figure 8. Here is how to create those flows:

![](images/sqsFlows.png)

**Send Message Flow:** This is the flow which sends a message to the Amazon SQS queue.
Start by dropping an HTTP endpoint on the flow from the palette. The only parameters you need to configure for this endpoint is "Host", "Port", and "Path". Change host to "localhost", port to "8081", and Path to "sqssend". This is the URL you'd call to start this flow. Then drop an Amazon SQS connector from the palette onto the endpoint. In the configuration box for the Amazon SQS connector, select the previously created Amazon SQS configuration from the Config Reference dropdown menu. Change the operation to "Send message", and set the Message to "#[header:INBOUND:message]". Click okay.  

![](images/sqsAmazonConfig1.png)

This completes the Send Message flow.


**Receive Message Flow:** This is the flow which registers and logs that the message has been received. Drag a new Amazon SQS Connector from the palette to directly below the Send Message flow (not into a Flow context). This will create a new Flow with an Inbound Amazon SQS (Streaming) endpoint. In the configuration box for the Amazon SQS connector, select the previously created Amazon SQS configuration from the Config Reference dropdown menu. The operation will be set to "Receive messages" and the other default values are fine.
Drag a Logger component onto the flow after the Amazon SQS connector. In the configuration box for the Logger component, set Message to "Recieved message: #[payload]".

![](images/sqsAmazonConfig2.png)

This completes the Receive Message flow.


**Flow XML**

The final flow XML should look like this. Pay attention "accessKey" and "secretKey" will be different for your app than the values here. 

    <?xml version="1.0" encoding="UTF-8"?>
    
    <mule xmlns:http="http://www.mulesoft.org/schema/mule/http" xmlns:sqs="http://www.mulesoft.org/schema/mule/sqs" xmlns="http://www.mulesoft.org/schema/mule/core" xmlns:doc="http://www.mulesoft.org/schema/mule/documentation" xmlns:spring="http://www.springframework.org/schema/beans" version="EE-3.4.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-current.xsd
    http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd
    http://www.mulesoft.org/schema/mule/http http://www.mulesoft.org/schema/mule/http/current/mule-http.xsd
    http://www.mulesoft.org/schema/mule/sqs http://www.mulesoft.org/schema/mule/sqs/1.0/mule-sqs.xsd">

    	<sqs:config name="Amazon_SQS" accessKey="XXXXXXXXXXXXXXXXXXXX" secretKey="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" queueName="muletest" doc:name="Amazon SQS">
    		<sqs:connection-pooling-profile initialisationPolicy="INITIALISE_ONE" exhaustedAction="WHEN_EXHAUSTED_GROW"/>
    	</sqs:config>

    	<flow name="sqs-demoFlow1" doc:name="sqs-demoFlow1">
    		<http:inbound-endpoint exchange-pattern="request-response" host="localhost" port="8081" path="sqssend" doc:name="HTTP"/>
    		<sqs:send-message config-ref="Amazon_SQS" message="#[header:INBOUND:message]" doc:name="Amazon SQS"/>
    	</flow>

    	<flow name="sqs-demoFlow2" doc:name="sqs-demoFlow2">
    		<sqs:receive-messages config-ref="Amazon_SQS" pollPeriod="10000" doc:name="Amazon SQS (Streaming)"/>
    		<logger message="Recieved message: #[payload]" level="INFO" doc:name="Logger"/>
    	</flow>
    </mule>


**Testing the app**

Now it's time to test the app. Run the app in Mule Studio and open a browser window. Visit [http://localhost:8081/sqssend?message=mules](http://localhost:8081/sqssend?message=mules). This will send a message to the Amazon SQS queue. Now switch back to Mule Studio and confirm that Studio has logged the message. You should see an entry in the Studio Console window that looks like:

    INFO  2013-06-03 08:55:45,161 [[sqs-demo].sqs-demoFlow2.stage1.02] org.mule.api.processor.LoggerMessageProcessor: Recieved message: mules


Other resources
===============

For more information on:

●     Amazon SQS connector, please visit [http://www.mulesoft.org/extensions/amazon-sqs](http://www.mulesoft.org/extensions/amazon-sqs)   
●     Mule platform and how to build Mule apps, please visit  [http://www.mulesoft.org/documentation](http://www.mulesoft.org/documentation/display/current/Home)

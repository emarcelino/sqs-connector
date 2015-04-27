Anypoint Amazon SQS Connector Demo
==================================


INTRODUCTION
------------
The Amazon SQS connector demo consists of the following projects:

* sqs-operations-demo - Provides sample flows on how to make use of various operations supported by the connector. Most of the flows are developed based on the combination of related operations to make a logical outcome.

HOW TO RUN DEMO
---------------

### Prerequisites
In order to build and run this project, you'll need;

* Anypoint Studio with Mule ESB 3.6 Runtime.
* Anypoint Amazon SQS Connector v3.0.0 or higher.
* Amazon SQS Credentials.

### Test the flows

With Anypoint Studio up and running, open the Import wizard from the File menu. A pop-up wizard will offer you the chance to pick Anypoint Studio Project from External Location. On the next wizard window point Project Root to the location of the demo project and select the Server Runtime as Mule Server 3.6.0 CE or EE. Once successfully imported the studio will automatically present the Anypoint Flows.

From the Package Explorer view, expand the demo project and open the mule-app.properties file. Fill in the credentials of Amazon SQS instance.

sqs-operations-demo : Run the demo project and with in the browser the demo includes the following options:

* Add Permissions to the queue : http://localhost:8081/addpermission?label=%20AliceSendMessage&accountId=055970264539&action=SendMessage
* Create and delete a queue : http://localhost:8081/createdeletequeue?name=AliceQueue
* Send & Receive Message : http://localhost:8081/sendmessage?msg=%27This%20is%20AliceQueue%27, This option depends on two flows (sqs-send-message-operation-demo-flow, sqs-receive-delete-message-operations-demo-flow). Since ReceiveMessage operation is an inbound message processor, it automatically picks the message from queue.

SUMMARY
-------

Congratulations! The demo present a limited list of operations supported by the connector, refer to the connector API docs for further possibilities.
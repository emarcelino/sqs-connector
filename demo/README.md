Mule SQS Connector Studio Demo
==============================

Mule Studio demo for Amazon SQS connector.

How to Run Demo
---------------

Import the project folder in Studio, specify your credentials in /src/main/resources/mule.properties and run the application.

About the Demo
--------------

The demo includes the following options:
* Send a message to the queue: http://localhost:8081/status?message=[message]
* Pull a message from the queue and write it in a file (under /tmp/amazon-sqs)
* Get queue url: http://localhost:8081/getUrl
* Get queue attributes: http://localhost:8081/getQueueAttribute

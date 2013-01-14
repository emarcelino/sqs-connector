SQS Demo
======================

INTRODUCTION
   This demo shows how to push and pull some message to the Amazon Simple Queue Service

HOW TO DEMO:
  1. Set the following environment variables:
    * sqsAccessKey
    * sqsSecretKey
    * mongoUsername (Optional because mongo don't use credentials by default)
    * mongoPassword (Optional because mongo don't use credentials by default)

  2. Run the SQSFunctionalTestDriver, or deploy this demo an a Mule Container. 
    a. Add a status to your mongo DB 
        Run the testPutSomeStatusInMongo test or alternatively hit 
        http://localhost:9090/add-status-to-mongo, passing as query params the status text.
        Example: http://localhost:9090/add-status-to-mongo?status=...
        

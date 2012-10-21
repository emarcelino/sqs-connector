SQS Demo
======================

INTRODUCTION
   This demo shows how to push and pull some message to the Amazon Simple Queue Service

HOW TO DEMO:
  1. Set the following system variables:
    * sqsAccessKey - amazon access key
    * sqsSecretKey - amazon secret key
    * statusDir - directory to store the messages sent

  2. Run the SQSFunctionalTestDriver, or deploy this demo an a Mule Container. 
    a. store status as a file
        Run the testProduceAndConsume test or alternatively run curl
        Example:
            curl -X POST -d 'happy camper' http://localhost:9090/status
        

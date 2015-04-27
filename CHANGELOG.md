Amazon SQS Anypoint Connector Release Notes
==========================================

Date: 10-June-2015

Version: 3.0.0

Supported Mule Runtime Versions: 3.5.x or Higher

Supported API versions:
-----------------------

AWS SDK for Java : v1.9.30 

Supported Actions:

* AddPermission
* ChangeMessageVisibility
* ChangeMessageVisibilityBatch
* CreateQueue
* DeleteMessage
* DeleteMessageBatch
* DeleteQueue
* GetQueueAttributes
* GetQueueUrl
* ListDeadLetterSourceQueues
* ListQueues
* PurgeQueue
* ReceiveMessage
* RemovePermission
* SendMessage
* SendMessageBatch
* SetQueueAttributes

Custom Actions:
* GetApproximateNumberOfMessages


New Features and Functionality
------------------------------
- Upgraded AWS SDK for Java to 1.9.30.
- Upgraded Anypoint Connector Devkit to 3.6.1
- The following new actions have been added :
* ChangeMessageVisibility
* ChangeMessageVisibilityBatch
* CreateQueue 
* DeleteMessageBatch
* ListDeadLetterSourceQueues
* ListQueues
* PurgeQueues
* SendMessageBatch
- The GetURL operation has been renamed to Get Queue URL.
- Added support for proxy settings.


Previous Releases
-----------------

### 2.5.5
- Upgraded Anypoint Devkit to 3.5.3 release.
- Added support for 3.7.0 Runtime.

### 2.5.4
- Updated the Devkit & Tested for 3.5.1 release.

### 2.5.1
- Added support for sending message attributes.
- Updated SDK version to 1.7.13.
- `receiveMessages` operation now uses asynchronous delivery instead of polling, significantly improving performance. The `pollPeriod` parameter has been preserved but deprecated to maintain backwards compatibility.
- Fixed message source threads never shutting down when Mule shuts down or redeploys, which caused applications to hang.

### 2.3.1
- Tested for 3.5.0 release.
- Updated for the latest versions of DevKit Annotations (e.g. removing @Default @Optional)
- Added Studio Interoperabilty Tests.
- Added a 65 second sleep between tests to cater for new SQS restriction on creating and deleting a queue of the same name within 60 seconds of itself.

### 2.2.0
- Migrated to AWS SDK
- Clean up files and dependencies

### 2.1.0
- Migrated to DevKit 3.4-RC1
- Added connectivity testing
- Updated documentation

### 2.0.0
- Upgraded to DevKit 3.3.2
- Fixed tests and examples
- Added Studio demo

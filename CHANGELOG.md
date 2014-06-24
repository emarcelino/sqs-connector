Amazon SQS Anypoint Connector Release Notes
==========================================

Date: 24-Jun-2014

Version: 2.5.1

Supported Mule Runtime Versions: 3.4.x, 3.5.0

Supported API versions:
-----------------------

1.3.31 SDK

Supported Operations:

* Add permission
* Delete Message
* Delete Queue
* Get approximate number of messages
* Get Queue Attributes
* Get URL
* Remove Permission
* Send Message
* Set Queue Attribute


New Features and Functionality
------------------------------

`receiveMessages` operation now uses asynchronous delivery instead of polling, significantly improving performance. The `pollPeriod` parameter has been preserved but deprecated to maintain backwards compatibility.

Fixed message source threads never shutting down when Mule shuts down or redeploys, which caused applications to hang.

Previous Releases
-----------------

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

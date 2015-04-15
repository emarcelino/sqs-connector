Amazon SQS Anypoint Connector Release Notes
==========================================

Date: 10-Apr-2015

Version: 2.5.5

Supported Mule Runtime Versions: 3.5.x or Higher

Supported API versions:
-----------------------

1.7.13 SDK

Supported Operations:

* Add permission
* Delete Message
* Delete Queue
* Get approximate number of messages
* Get Queue Attributes
* ****** Get URL renamed to Get Queue URL
* Remove Permission
* Send Message
* Set Queue Attribute


New Features and Functionality
------------------------------

- Upgraded Anypoint Devkit to 3.5.3 release.
- Added support for 3.7.0 Runtime.

Previous Releases
-----------------

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

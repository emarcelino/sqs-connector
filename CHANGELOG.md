Amazon SQS Anypoint Connector Release Notes
==========================================

Date: 07-May-2014

Version: 2.3.1

Supported API versions: 1.3.31 SDK

Supported Mule Runtime Versions: 3.4.x, 3.5.0

New Features and Functionality
------------------------------

Tested for 3.5.0 release.

Updated for the latest versions of DevKit Annotations (e.g. removing @Default @Optional)
Added Studio Interoperabilty Tests.
Added a 65 second sleep between tests to cater for new SQS restriction on creating and deleting a queue of the same name within 60 seconds of itself.

Previous Release
------------------------------

2.2.0
=====
- Migrated to AWS SDK
- Clean up files and dependencies

2.1.0
=====
- Migrated to DevKit 3.4-RC1
- Added connectivity testing
- Updated documentation

2.0.0
=====
- Upgraded to DevKit 3.3.2
- Fixed tests and examples
- Added Studio demo

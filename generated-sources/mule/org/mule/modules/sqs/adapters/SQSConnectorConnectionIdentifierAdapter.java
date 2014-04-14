
package org.mule.modules.sqs.adapters;

import javax.annotation.Generated;
import org.mule.modules.sqs.SQSConnector;
import org.mule.modules.sqs.connection.Connection;


/**
 * A <code>SQSConnectorConnectionIdentifierAdapter</code> is a wrapper around {@link SQSConnector } that implements {@link org.mule.devkit.dynamic.api.helper.Connection} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-14T12:28:26-05:00", comments = "Build M4.1875.17b58a3")
public class SQSConnectorConnectionIdentifierAdapter
    extends SQSConnectorProcessAdapter
    implements Connection
{


    public String getConnectionIdentifier() {
        return super.getAccessKey();
    }

}

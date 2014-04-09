
package org.mule.modules.sqs.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.modules.sqs.adapters.SQSConnectorConnectionIdentifierAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-09T09:25:08-05:00", comments = "Build M4.1875.17b58a3")
public class SQSConnectorConnectionFactory implements KeyedPoolableObjectFactory
{

    private static Logger logger = LoggerFactory.getLogger(SQSConnectorConnectionFactory.class);
    private SQSConnectorConnectionManager connectionManager;

    public SQSConnectorConnectionFactory(SQSConnectorConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Object makeObject(Object key)
        throws Exception
    {
        if (!(key instanceof SQSConnectorConnectionKey)) {
            if (key == null) {
                logger.warn("Connection key is null");
            } else {
                logger.warn("Cannot cast key of type ".concat(key.getClass().getName().concat(" to ").concat("org.mule.modules.sqs.connectivity.SQSConnectorConnectionKey")));
            }
            throw new RuntimeException("Invalid key type ".concat(key.getClass().getName()));
        }
        SQSConnectorConnectionIdentifierAdapter connector = new SQSConnectorConnectionIdentifierAdapter();
        connector.setRegion(connectionManager.getRegion());
        connector.setQueueUrl(connectionManager.getQueueUrl());
        if (connector instanceof MuleContextAware) {
            ((MuleContextAware) connector).setMuleContext(connectionManager.getMuleContext());
        }
        if (connector instanceof Initialisable) {
            ((Initialisable) connector).initialise();
        }
        if (connector instanceof Startable) {
            ((Startable) connector).start();
        }
        if (!connector.isConnected()) {
            connector.connect(((SQSConnectorConnectionKey) key).getAccessKey(), ((SQSConnectorConnectionKey) key).getSecretKey(), ((SQSConnectorConnectionKey) key).getQueueName());
        }
        return connector;
    }

    public void destroyObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof SQSConnectorConnectionKey)) {
            if (key == null) {
                logger.warn("Connection key is null");
            } else {
                logger.warn("Cannot cast key of type ".concat(key.getClass().getName().concat(" to ").concat("org.mule.modules.sqs.connectivity.SQSConnectorConnectionKey")));
            }
            throw new RuntimeException("Invalid key type ".concat(key.getClass().getName()));
        }
        if (!(obj instanceof SQSConnectorConnectionIdentifierAdapter)) {
            if (obj == null) {
                logger.warn("Connector is null");
            } else {
                logger.warn("Cannot cast connector of type ".concat(obj.getClass().getName().concat(" to ").concat("org.mule.modules.sqs.adapters.SQSConnectorConnectionIdentifierAdapter")));
            }
            throw new RuntimeException("Invalid connector type ".concat(obj.getClass().getName()));
        }
        try {
            ((SQSConnectorConnectionIdentifierAdapter) obj).disconnect();
        } catch (Exception e) {
            throw e;
        } finally {
            if (((SQSConnectorConnectionIdentifierAdapter) obj) instanceof Stoppable) {
                ((Stoppable) obj).stop();
            }
            if (((SQSConnectorConnectionIdentifierAdapter) obj) instanceof Disposable) {
                ((Disposable) obj).dispose();
            }
        }
    }

    public boolean validateObject(Object key, Object obj) {
        if (!(obj instanceof SQSConnectorConnectionIdentifierAdapter)) {
            if (obj == null) {
                logger.warn("Connector is null");
            } else {
                logger.warn("Cannot cast connector of type ".concat(obj.getClass().getName().concat(" to ").concat("org.mule.modules.sqs.adapters.SQSConnectorConnectionIdentifierAdapter")));
            }
            throw new RuntimeException("Invalid connector type ".concat(obj.getClass().getName()));
        }
        try {
            return ((SQSConnectorConnectionIdentifierAdapter) obj).isConnected();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public void activateObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof SQSConnectorConnectionKey)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof SQSConnectorConnectionIdentifierAdapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
            if (!((SQSConnectorConnectionIdentifierAdapter) obj).isConnected()) {
                ((SQSConnectorConnectionIdentifierAdapter) obj).connect(((SQSConnectorConnectionKey) key).getAccessKey(), ((SQSConnectorConnectionKey) key).getSecretKey(), ((SQSConnectorConnectionKey) key).getQueueName());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void passivateObject(Object key, Object obj)
        throws Exception
    {
    }

}

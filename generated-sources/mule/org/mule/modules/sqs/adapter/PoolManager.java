
package org.mule.modules.sqs.adapter;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:43:51-05:00", comments = "Build master.1915.dd1962d")
public interface PoolManager {

    /**
     * Retrieves the pool of objects
     */
    public org.mule.util.pool.LifecyleEnabledObjectPool getLifecyleEnabledObjectPool();
}
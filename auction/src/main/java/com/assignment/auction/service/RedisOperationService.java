/**
 * @author yukti.gupta
 * @date 28/04/23
 * @project_name auction
 **/

package com.assignment.auction.service;

import org.redisson.api.RBucket;

import java.util.concurrent.TimeUnit;

public interface RedisOperationService {
    RBucket<Object> acquire(String lockName, TimeUnit timeUnit, int count);

    void release(RBucket rLock);
}

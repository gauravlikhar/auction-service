///**
// * @author yukti.gupta
// * @date 28/04/23
// * @project_name auction
// **/
//
//package com.assignment.auction.service.impl;
//
//import com.assignment.auction.service.RedisOperationService;
//import lombok.extern.log4j.Log4j2;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service
//@Log4j2
//public class RedisOperationServiceImpl implements RedisOperationService {
//
////    @Autowired
////    private RedissonClient redissonClient;
////    @Override
////    public RBucket<Object> acquire(String lockName, TimeUnit timeUnit, int count) {
////
////        RBucket<Object> rbBucket = redissonClient.getBucket(lockName);
////        try {
////            if(rbBucket.trySet(0, count, timeUnit)) {
////                log.info("Acquiring Lock for ["+lockName+"]");
////                return rbBucket;
////            }
////            log.info("Lock acquisition failed for key -- {}",lockName);
////        }
////        catch(Exception e) {
////            log.info("Error occurred while acquiring the lock {}",lockName, e);
////        }
////        return null;
////    }
////
////    @Override
////    public void release(RBucket rBucket) {
////        log.info("Releasing Lock -- {}", rBucket.getName());
////        rBucket.delete();
////    }
//}

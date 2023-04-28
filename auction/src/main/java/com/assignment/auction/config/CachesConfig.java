/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachesConfig {

//    @Bean
//    public RedissonClient redissonClient() {
//        String address ="redis://beta-redis-common.stanzaliving.com:6379";
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress(address)
//                .setDatabase(0)
//                .setConnectionPoolSize(20)
//                .setConnectionMinimumIdleSize(2)
//                .setConnectTimeout(5000)
//                .setIdleConnectionTimeout(120000);
//        return Redisson.create(config);
//    }
}

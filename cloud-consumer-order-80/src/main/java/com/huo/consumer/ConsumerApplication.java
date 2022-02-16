package com.huo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 　 功能描述
 *
 * 　 @version 1.0
 * 　 @author huoqy
 * 　 @createDate 2021年12月13日 14:07
 * 　 @since JDK1.8
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}

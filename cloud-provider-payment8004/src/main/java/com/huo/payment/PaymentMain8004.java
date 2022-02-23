package com.huo.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 　 功能描述
 *
 * 　 @version 1.0
 * 　 @author huoqy
 * 　 @createDate 2021年12月21日 14:21
 * 　 @since JDK1.8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }

}

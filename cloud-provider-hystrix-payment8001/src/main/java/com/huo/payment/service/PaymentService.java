package com.huo.payment.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

/**
 * 　 功能描述
 *
 * 　 <p>
 * 　 -----------------------------------------------------------------------------
 * 　 <p>
 * 　 工程名 ： cloud
 * 　 <p>
 * 　 授权 : (C) Copyright topwalk Corporation 2014-2022
 * 　 <p>
 * 　 公司 : 托尔思天行网安信息技术有限责任公司
 * 　 <p>
 * 　 ------------------------------------------------------------------- ----------
 * 　 <p>
 * 　 <font color="#FF0000">注意: 本内容仅限于拓尔思天行网安公司内部使用，禁止转发</font>
 * 　 <p>
 *
 * 　 @version 1.0
 * 　 @author huoqy
 * 　 @createDate 2022年02月23日 16:47
 * 　 @since JDK1.8
 */
@Service
@Slf4j
public class PaymentService {

    /**
     * 正常访问
     */
    public String paymentInfo_Ok(Integer id){
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_Ok, id :" + id;
    }

    /**
     *  异常的方法
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id :" + id;
    }

    /**
     * 服务降级的方法
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id :" + id;
    }

    /**
     * 服务熔断的方法
     */

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" +"调用成功，流水号： " + serialNumber;
    }
}

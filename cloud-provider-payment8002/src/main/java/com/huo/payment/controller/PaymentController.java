package com.huo.payment.controller;

import com.huo.common.entities.CommonResult;
import com.huo.common.entities.Payment;
import com.huo.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 　 功能描述
 *
 * 　 <p>
 * 　 -----------------------------------------------------------------------------
 * 　 <p>
 * 　 工程名 ： cloud
 * 　 <p>
 * 　 授权 : (C) Copyright topwalk Corporation 2014-2021
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
 * 　 @createDate 2021年12月10日 15:30
 * 　 @since JDK1.8
 */
@RestController

@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     *  服务发现
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/save")
    public CommonResult<Payment> save(@RequestBody Payment payment){
        int res = paymentService.create(payment);
        log.info("插入结果："+res);
        if (res > 0){
            return new CommonResult(200,"插入成功,端口号："+serverPort);
        } else {
            return new CommonResult(206,"失败");
        }

    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getById(@PathVariable Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("查询。。。");
        if (Objects.isNull(payment)){
            return new CommonResult(206,"查询失败");
        } else {
            return new CommonResult(200,"查询成功,端口号："+serverPort,payment);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("#####element"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}

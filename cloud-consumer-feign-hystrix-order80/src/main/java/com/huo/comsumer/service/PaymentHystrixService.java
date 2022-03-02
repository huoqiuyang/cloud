package com.huo.comsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
 * 　 @createDate 2022年02月24日 15:09
 * 　 @since JDK1.8
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",
            fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {


    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_Ok(@PathVariable(value = "id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable(value = "id") Integer id);
}

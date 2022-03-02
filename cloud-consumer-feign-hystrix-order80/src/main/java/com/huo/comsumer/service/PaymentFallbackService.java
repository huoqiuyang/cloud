package com.huo.comsumer.service;

import org.springframework.stereotype.Component;

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
 * 　 @createDate 2022年03月02日 17:03
 * 　 @since JDK1.8
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "paymentInfo_Ok的fallback方法";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout的fallback方法";
    }
}

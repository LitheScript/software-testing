package com.example.demo.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.demo.controller.dto.Alipay;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *@author cj
 *@date 2022/6/11 20:41
 * @param
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Resource
    private OrderMapper orderMapper;

    @GetMapping("/pay")
    public String pay(Alipay aliPay) {
        AlipayTradePagePayResponse response;
        try {
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(aliPay.getSubject(), aliPay.getTraceNo(), aliPay.getTotalAmount(), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return response.getBody();
    }


    @SuppressWarnings("checkstyle:Regexp")
    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");

            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
//                orderMapper.updateState(tradeNo, 1, gmtPayment);
                int i = 0;
                i++;
            }
        }
        return "success";
    }

}

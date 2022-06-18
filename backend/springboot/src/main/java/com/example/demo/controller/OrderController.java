package com.example.demo.controller;


import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.Result;
//import com.example.demo.entity.Order;
import com.example.demo.service.impl.ObjectServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderServiceImpl orderService;
    @Resource
    ObjectServiceImpl objectService;
    @Resource
    UserServiceImpl userService;


    @GetMapping("getOrderList")
    public Result<?> getOrderList(int userId) {
        List<JSONObject> orderList;
        orderList = orderService.getOrderList(userId);
        if (orderList.isEmpty()) {
            return Result.error("-1", "没有订单记录");
        } else {
            return Result.success(orderList);
        }
    }


    @PostMapping("generateOrder")
    public Result<?> generateOrder(int objectId, int borrowerId, String lentoutTime, String returnTime, String campus) {
        LocalDateTime parseLentOutTime = LocalDateTime.parse(lentoutTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime parseReturnTime = LocalDateTime.parse(returnTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int orderId = orderService.generateOrder(objectId, borrowerId, parseLentOutTime, parseReturnTime, campus);
        if (orderId == -1) {
            return Result.error("-1", "租借时间错误");
        } else if (objectId == -2) {
            return Result.error("-2", "订单生成失败");
        } else {
            return Result.success(orderId);
        }
    }

    @GetMapping("confirm")
    public Result<?> confirm(int orderId) {
        int flag = orderService.confirm(orderId);
        if (flag == -1) {
            return Result.error("-1", "确认收货失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("cancelOrder")
    public Result<?> cancelOrder(int orderId) {
        int flag = orderService.cancelOrder(orderId);
        if (flag == -1) {
            return Result.error("-1", "未找到相应订单");
        } else if (flag == -2) {
            return Result.error("-2", "订单已支付");
        } else if (flag == -3) {
            return Result.error("-3", "订单取消失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("pay")
    public Result<?> pay(Integer orderId) {
        int count = orderService.pay(orderId);
        String payUrl = "http://101.35.194.132:9090/alipay/pay?subject="
                + objectService.getById((orderService.getById(orderId).getObjectId())).getName()
                + "&traceNo=" + orderId + "&totalAmount=" + orderService.getById(orderId).getRentTotal();
        Integer userId = orderService.getById(orderId).getBorrowerId();
        userService.getById(userId).setReputation(userService.getById(userId).getReputation() + 5);
        if (count == -1) {
            return Result.error("-1", "订单不存在");
        } else if (count == -2) {
            return Result.error("-2", "操作失败");
        } else {
            return Result.success(payUrl);
        }
    }

}

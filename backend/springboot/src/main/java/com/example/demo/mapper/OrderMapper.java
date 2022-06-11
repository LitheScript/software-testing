package com.example.demo.mapper;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<JSONObject> getOrderList(Integer userId);

    String getObjectName(Integer orderId);
}

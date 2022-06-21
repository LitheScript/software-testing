package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Order;
import com.example.demo.entity.Remarks;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.RemarksMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 评价表
 * 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class RemarksServiceImpl extends ServiceImpl<RemarksMapper, Remarks> implements IService<Remarks> {
    @Resource
    RemarksMapper remarksMapper;
    @Resource
    OrderMapper orderMapper;

    public JSONObject getRemarkList(Integer objectId) {
        JSONObject jsonObject = new JSONObject();

        List<JSONObject> remarkList = remarksMapper.getRemarkList(objectId);
        jsonObject.put("remarkList", remarkList);
        int count = remarkList.size();
        jsonObject.put("commentNum", count);
        return jsonObject;
    }

    public Integer giveRemark(Integer objectId, Integer userId, String content, Integer grade, Integer type) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("object_id", objectId);
        wrapper.eq("borrower_id", userId);
        Order order = orderMapper.selectOne(wrapper);
        if (order == null) {
            return -1;
        }
        Integer orderId = order.getOrderId();
        if (!Objects.equals(orderMapper.selectById(orderId).getStatus(), "待评价")) {
            return -2;
        }
        if (!Objects.equals(orderMapper.selectById(orderId).getBorrowerId(), userId)) {
            return -3;
        }
        Remarks remark = new Remarks();
        remark.setUserId(userId);
        remark.setObjectId(objectId);
        remark.setTime(LocalDateTime.now());
        remark.setContent(content);
        remark.setGrade(grade);
        remark.setType(type);
        int count = remarksMapper.insert(remark);
        if (count <= 0) {
            return -4;
        } else {
            order = new Order();
            order.setOrderId(orderId);
            order.setStatus("已评价");
            orderMapper.updateById(order);
            return remark.getRemarkId();
        }
    }


}

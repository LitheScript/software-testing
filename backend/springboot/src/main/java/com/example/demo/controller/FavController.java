package com.example.demo.controller;


import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
//import com.example.demo.entity.Fav;
import com.example.demo.service.impl.FavServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关系表 前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/fav")
public class FavController {
    @Resource
    FavServiceImpl favService;

    @Resource
    ObjectController objectController;

    @PostMapping("collect")
    public Result<?> collect(Integer favId, Integer objectId) {
        Integer count = favService.collect(favId, objectId);
        if (count <= 0) {
            return Result.error("-1", "收藏失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("deleteCollection")
    public Result<?> deleteCollection(Integer favId, Integer objectId) {
        Integer count = favService.deleteCollection(favId, objectId);
        if (count <= 0) {
            return Result.error("-1", "收藏删除失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("queryObjectInFav")//判断用户是否收藏了该商品
    public Result<?> queryObjectInFav(Integer objectId, Integer userId) {
        Integer favId = favService.queryObjectInFav(objectId, userId);
        if (favId == null) {
            return Result.error("-1", "用户未收藏该物品");
        } else {
            return Result.success(favId);
        }
    }

    @GetMapping("viewFavouriteCart")
    public Result<?> viewFavouriteCart(Integer favId) {
        List<JSONObject> objectDetails = favService.viewFavouriteCart(favId);
        if (objectDetails == null) {
            return Result.error("-1", "收藏夹不存在");
        }
        if (objectDetails.isEmpty()) {
            return Result.error("-2", "该收藏夹为空");
        } else {
            return Result.success(objectDetails);
        }

    }
}


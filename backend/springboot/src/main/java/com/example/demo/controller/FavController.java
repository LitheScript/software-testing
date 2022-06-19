package com.example.demo.controller;


import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
//import com.example.demo.entity.Fav;
import com.example.demo.mapper.FavMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.impl.FavServiceImpl;
import org.springframework.web.bind.annotation.*;

//import org.springframework.stereotype.Controller;

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
    FavMapper favMapper;

    @Resource
    ObjectController objectController;

    @PostMapping("collect")
    public Result<?> collect(@RequestParam("favId") Integer favId, @RequestParam("objectId") Integer objectId) {
        if (favId != 1 || objectId < 0) {
            return Result.error("-1", "收藏失败");
        }

        if (favService.isExist(favId, objectId) != 0) {
            return Result.error("-1", "已收藏，收藏失败");
        }
        Integer count = favService.collect(favId, objectId);
        if (count <= 0) {
            return Result.error("-1", "收藏失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("deleteCollection")
    public Result<?> deleteCollection(@RequestParam(value = "favId", required = true) Integer favId, @RequestParam(value = "objectId", required = true) Integer objectId) {
        if (favId < 0 || objectId < 0) {
            return Result.error("-1", "数据有误，删除失败");
        }

        if (favService.isExist(favId, objectId) == 0) {
            return Result.error("-1", "未收藏，删除失败");
        }
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


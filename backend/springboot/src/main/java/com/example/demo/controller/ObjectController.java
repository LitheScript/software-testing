package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.common.Result;
import com.example.demo.entity.Object;

import com.example.demo.mapper.ObjectMapper;
import com.example.demo.service.impl.ObjectServiceImpl;
//import io.swagger.models.auth.In;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/object")
public class ObjectController {

    private final String noPicUrl = "https://ss1.baidu.com/9vo3dSag_xI4k"
            + "hGko9WTAnF6hhy/zhidao/pic/item/b8389b504fc2d5624cd052f9e11190ef77c66cce.jpg";


    @Resource
    ObjectMapper objectMapper;

    @Resource
    ObjectServiceImpl objectService;

    @UserLoginToken
    @PostMapping
    public Result<?> upload(@RequestBody Object object) {
        objectMapper.insert(object);
        return Result.success();
    }

    @UserLoginToken
    @PostMapping("/getList")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Object> wrapper = Wrappers.<Object>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Object::getName, search);
        }
        Page<Object> userPage = objectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }


    @GetMapping("getObjectDetail")
    public Result<?> getObjectDetail(@RequestParam(value = "objectId", required = true) int objectId) {
        JSONObject jsonObject;
        jsonObject = objectService.getObjectDetail(objectId);
        if (jsonObject == null) {
            return Result.error("-1", "暂时无法查看该物品信息");
        } else {
            return Result.success(jsonObject);
        }
    }

    @GetMapping("getObjectListByType")
    public Result<?> getObjectListByType(String type, Integer page) {
        Page<JSONObject> iPage = new Page<JSONObject>(page, 6);
        iPage = objectService.getObjectListByType(type, iPage);
        return Result.success(iPage);

    }

    @GetMapping("recommend")
    public Result<?> recommend() {
        List<JSONObject> jsonObjects;
        jsonObjects = objectService.recommend();
        if (jsonObjects.isEmpty()) {
            return Result.error("-1", "暂无商品信息");
        } else {
            return Result.success(jsonObjects);
        }
    }

    @GetMapping("searchObject")
    public Result<?> searchObject(@RequestParam(value = "name", required = true) String name, @RequestParam("page") Integer page) {

        if (name.equals("")) {
            return Result.error("-1", "名字不能为空");
        }
        Page<JSONObject> iPage = new Page<JSONObject>(page, 6);
        if (name == null) {
            name = "%";
        }
        iPage = objectService.searchObject(name, iPage);
        if (iPage.getTotal() == 0) {
            return Result.error("-1", "暂无商品信息");
        } else {
            return Result.success(iPage);
        }
    }

    @PostMapping("uploadObject")
    public Result<?> uploadObject(Integer userId, String name, String description,
                                  Integer deposit, String tag, Integer rentDaily,
                                  String newLevel, @RequestParam(defaultValue = noPicUrl) String url) {
        Integer objectId = objectService.uploadObject(userId, name, description, deposit, tag, rentDaily, newLevel, url);
        if (objectId == -1) {
            return Result.error("-1", "上传失败");
        } else {
            return Result.success(objectId);
        }
    }

    @PostMapping("modifyObject")
    public Result<?> modifyObject(Integer objectId, String name,
                                  String description, Integer deposit,
                                  String tag, Integer rentDaily, String newLevel) {
        Integer count = objectService.modifyObject(objectId, name, description, deposit, tag, rentDaily, newLevel);
        if (count <= 0) {
            return Result.error("-1", "修改失败");
        } else {
            return Result.success();
        }
    }

    @GetMapping("viewOwnObjectList")
    public Result<?> viewOwnObjectList(Integer userId) {
        List<JSONObject> jsonObjects = objectService.viewOwnObjectList(userId);
        if (jsonObjects.isEmpty()) {
            return Result.error("-1", "当前用户没有物品列表");
        } else {
            return Result.success(jsonObjects);
        }

    }

    public ObjectServiceImpl getObjectService() {
        return objectService;
    }

    @GetMapping("viewPendingObject")
//    public Result<?> viewPendingObject(Integer page){
//        Page<Object> iPage = new Page<>(page, 10);
//        Page<Object> objectPage = objectService.viewPendingObject(iPage);
//        if(objectPage.getTotal()==0)
//            return Result.error("-1", "当前没有待审核物品");
//        else return Result.success(objectPage);
//    }
    public Result<?> viewPendingObject(Integer page) {
        Page<JSONObject> iPage = new Page<JSONObject>(page, 6);
        iPage = objectService.viewPendingObject(iPage);
        return Result.success(iPage);

    }

    @GetMapping("verifyObject")
    public Result<?> verifyObject(Integer objectId, Boolean check) {
        Object object = new Object();
        if (check == Boolean.TRUE) {
            object.setStatus("审核通过");
        } else {
            object.setStatus("未通过审核");
        }
        object.setObjectId(objectId);
        if (objectService.updateById(object) == Boolean.TRUE) {
            return Result.success();
        } else {
            return Result.error("-1", "审核操作失败");
        }
    }

    @GetMapping("removeObject")
    public Result<?> removeObject(Integer objectId) {
        Object object = new Object();
        object.setObjectId(objectId);
        if (objectService.removeById(objectId) == Boolean.TRUE) {
            return Result.success();
        } else {
            return Result.error("-1", "下架操作失败");
        }
    }
}


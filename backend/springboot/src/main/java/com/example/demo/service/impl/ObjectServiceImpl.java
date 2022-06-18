package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Object;
import com.example.demo.entity.Picture;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.mapper.PictureMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class ObjectServiceImpl extends ServiceImpl<ObjectMapper, Object> implements IService<Object> {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    PictureMapper pictureMapper;

    public JSONObject getObjectDetail(Integer objectId) {
        JSONObject detail;
        detail = objectMapper.getObjectDetail(objectId);
        if (detail == null) {
            return null;
        }
        List<String> urls = objectMapper.getPictureUrl(objectId);
        Integer rentCount = objectMapper.getRentCount(objectId);
        detail.put("urls", urls);
        detail.put("rentCount", rentCount);
        return detail;
    }


    public Page<JSONObject> getObjectListByType(String type, Page<JSONObject> ipage) {
        return objectMapper.getObjectListByType(type, ipage);

    }

    public List<JSONObject> recommend() {
        return objectMapper.recommend();

    }

    public Page<JSONObject> searchObject(String name, Page<JSONObject> ipage) {
        return objectMapper.searchObject(name, ipage);
    }

    public Integer uploadObject(Integer userId, String name,
                                String description, Integer deposit, String tag, Integer rentDaily, String newLevel, String url) {
        Object object = new Object();
        object.setUserId(userId);
        object.setName(name);
        object.setDescription(description);
        object.setDeposit(deposit);
        object.setTag(tag);
        object.setRentDaliy(rentDaily);
        object.setNewLevel(newLevel);
        object.setStatus("待审核"); //管理员部分待做
        int count = objectMapper.insert(object);
        if (count <= 0) {
            return -1;
        } else {
            Integer objectId = object.getObjectId();
            Picture picture = new Picture();
            picture.setObjectId(objectId);
            picture.setPictureUrl(url);
            picture.setType(1);
            if (pictureMapper.insert(picture) <= 0) {
                return -1;
            } else {
                return objectId;
            }
        }
    }

    public Integer modifyObject(Integer objectId, String name, String description, Integer deposit, String tag, Integer rentDaily, String newLevel) {
        Object object = new Object();
        object.setObjectId(objectId);
        if (name != null) {
            object.setName(name);
        }
        if (description != null) {
            object.setDescription(description);
        }
        if (deposit != null) {
            object.setDeposit(deposit);
        }
        if (tag != null) {
            object.setTag(tag);
        }
        if (rentDaily != null) {
            object.setRentDaliy(rentDaily);
        }
        if (newLevel != null) {
            object.setNewLevel(newLevel);
        }
        object.setStatus("审核通过"); //管理员部分待做
        return objectMapper.updateById(object);
    }

    public List<JSONObject> viewOwnObjectList(Integer userId) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Object> objects = objectMapper.selectList(wrapper);
        if (objects.isEmpty()) {
            return null;
        }
        for (Object object : objects) {
            QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
            String url;
            pictureQueryWrapper.eq("object_id", object.getObjectId());
            Picture picture = pictureMapper.selectOne(pictureQueryWrapper);
            if (picture != null) {
                url = picture.getPictureUrl();
            } else {
                url = null;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("object", object);
            jsonObject.put("url", url);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    //    public Page<Object> viewPendingObject(IPage<Object> iPage){
//        QueryWrapper<Object> wrapper=new QueryWrapper<>();
//        wrapper.eq("status", "待审核");
//        return (Page<Object>) objectMapper.selectPage(iPage, wrapper);
//    }
    public Page<JSONObject> viewPendingObject(Page<JSONObject> ipage) {
        return objectMapper.viewPendingObject(ipage);

    }
}

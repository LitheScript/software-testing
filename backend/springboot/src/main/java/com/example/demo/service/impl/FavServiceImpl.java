package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Fav;
import com.example.demo.mapper.FavMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关系表 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class FavServiceImpl extends ServiceImpl<FavMapper, Fav> implements IService<Fav> {
    @Resource
    FavMapper favMapper;
    @Resource
    ObjectServiceImpl objectService;


    public Integer collect(Integer favId, Integer objectId) {
        int fav_id = favId;
        int object_id = objectId;
        Fav fav = new Fav();
        fav.setFavId(fav_id);
        fav.setObjectId(object_id);
        fav.setTime(LocalDateTime.now());
        return favMapper.insert(fav);
    }

    public Integer deleteCollection(Integer favId, Integer objectId) {
        int fav_id = favId;
        int object_id = objectId;
        QueryWrapper<Fav> wrapper = new QueryWrapper<>();
        wrapper.eq("fav_id", fav_id);
        wrapper.eq("object_id", object_id);
        return favMapper.delete(wrapper);
    }

    public Integer queryObjectInFav(Integer objectId, Integer userId) {
        return favMapper.queryObjectInFav(objectId, userId);
    }

    public List<JSONObject> viewFavouriteCart(Integer favId) {
        int fav_id = favId;
        QueryWrapper<Fav> wrapper = new QueryWrapper<>();
        wrapper.eq("fav_id", fav_id);
        List<Fav> favs = favMapper.selectList(wrapper);
        if (favs.isEmpty()) {
            return null;
        }
        List<JSONObject> objectDetails = new ArrayList<>();
        for (Fav fav : favs) {
            Integer objectId = fav.getObjectId();
            JSONObject objectDetail = objectService.getObjectDetail(objectId);
            if (objectDetail != null) {
                objectDetails.add(objectDetail);
            }
        }
        return objectDetails;


    }

    public int isExist(Integer favId, Integer objectId) {
        int fav_id = favId;
        Integer object_id = objectId;
        return favMapper.isExist(fav_id,object_id);
    }
}

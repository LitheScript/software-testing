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
        Fav fav = new Fav();
        fav.setFavId(favId);
        fav.setObjectId(objectId);
        fav.setTime(LocalDateTime.now());
        return favMapper.insert(fav);
    }

    public Integer deleteCollection(Integer favId, Integer objectId) {
        QueryWrapper<Fav> wrapper = new QueryWrapper<>();
        wrapper.eq("favId", favId);
        wrapper.eq("objectId", objectId);
        return favMapper.delete(wrapper);
    }

    public Integer queryObjectInFav(Integer objectId, Integer userId) {
        return favMapper.queryObjectInFav(objectId, userId);
    }

    public List<JSONObject> viewFavouriteCart(Integer favId) {
        QueryWrapper<Fav> wrapper = new QueryWrapper<>();
        wrapper.eq("favId", favId);
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
}

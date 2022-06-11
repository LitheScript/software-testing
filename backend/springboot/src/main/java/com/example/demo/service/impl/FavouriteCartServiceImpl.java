package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.FavouriteCart;
import com.example.demo.mapper.FavouriteCartMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class FavouriteCartServiceImpl extends ServiceImpl<FavouriteCartMapper, FavouriteCart> implements IService<FavouriteCart> {
    @Resource
    FavouriteCartMapper favouriteCartMapper;


    public List<FavouriteCart> favouriteCartList(Integer userId) {
        QueryWrapper<FavouriteCart> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        return favouriteCartMapper.selectList(wrapper);
    }

    public Integer addFavouriteCart(Integer userId, String name, String description) {
        FavouriteCart favouriteCart = new FavouriteCart();
        favouriteCart.setUserId(userId);
        favouriteCart.setName(name);
        favouriteCart.setDescription(description);
        favouriteCart.setTime(LocalDateTime.now());
        favouriteCart.setCoverimg(null);
        Integer count = favouriteCartMapper.insert(favouriteCart);
        if (count <= 0) {
            return -1;
        } else {
            return favouriteCart.getFavId();
        }
    }


}

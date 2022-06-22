package com.example.demo.mapper;

import com.example.demo.entity.Fav;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 关系表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
public interface FavMapper extends BaseMapper<Fav> {

    Integer queryObjectInFav(Integer object_id, Integer user_id);

    Integer isExist(Integer fav_id, Integer object_id) ;

}

package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.*;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 发帖的表 服务实现类
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IService<Post> {

    @Resource
    PostMapper postMapper;

    @Resource
    UserMapper userMapper;

    public Integer publishPost(Integer userId, String title, String content, Integer expectedPrice) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setContent(content);
        post.setTime(LocalDateTime.now());
        post.setExpectedPrice(expectedPrice);
        postMapper.insert(post);
        return post.getPostId();
    }

    public List<JSONObject> viewMyPosts(Integer userId) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Post> postList = postMapper.selectList(wrapper);
        if (postList.isEmpty()) {
            return null;
        }
        for (Post post : postList) {

            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, userId));
            String url = user.getAvatar();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("post", post);
            jsonObject.put("avator", url);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    public Integer deleteMyPost(Integer postId) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        return postMapper.delete(wrapper);
    }

}

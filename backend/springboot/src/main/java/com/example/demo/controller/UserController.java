package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.PassToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.common.Result;
//import com.example.demo.entity.Object;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
//import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @UserLoginToken
    @PostMapping("/getList")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    //新增
    @UserLoginToken
    @PostMapping("/insert")
    @ResponseBody
    public Result<?> save(User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    //登录
    @PassToken
    @PostMapping("/login")
    public Result<?> login(@RequestParam(value = "nickName", required = true) String nickName,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "role", required = true) String role) {
        JSONObject jsonObject = new JSONObject();
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getNickName, nickName));

        if (res == null
                || !res.getPassword().equals(password)
                || !res.getRole().equals(role)) {
            return Result.error("-1", "用户名或密码输入错误");
        } else {
            String token = JwtUtil.getToken(nickName, password);
            jsonObject.put("token", token);
            return Result.success(jsonObject);
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

    //注册
    @PassToken
    @PostMapping("/register")
    public Result<?> register(@RequestParam(value = "nickName", required = true) String nickName,
                              @RequestParam(value = "password", required = true) String password,
                              @RequestParam(value = "role", required = true) String role) {
        //判断用户名是否唯一
        role="0";
        if (nickName.equals("")) {
            return Result.error("-1", "用户名不能为空");
        } else if (password.equals("")) {
            password = "123456";
        }
        User user = new User();
        user.setNickName(nickName);
        user.setPassword(password);
        user.setRole(role);
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getNickName, nickName));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @Resource
    private UserServiceImpl userService;

    /**
     * 多表联查，一对多，分页
     *
     * @param page 当前页
     * @param size 每页条数
     */
    @GetMapping("pageTestA/{page}/{size}")
    public Page<User> pageTestA(@PathVariable Integer page, @PathVariable Integer size) {
        Page<User> iPage = new Page<User>(page, size);
        return userService.getAll(iPage);
    }

    //    @UserLoginToken
    @PostMapping("/getUserByToken")
    public User getUserByToken(@RequestBody String token) {
        String userNickname;
        try {
            DecodedJWT jwt = JWT.decode(token);
            jwt.getClaim("username").asString();
            userNickname = jwt.getClaim("username").asString();
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getNickName, userNickname));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @PostMapping("modifyUserInfo")
    public Result<?> modifyUserInfo(@RequestParam(required = true) Integer userId,
                                    @RequestParam(required = false) String nickName,
                                    @RequestParam(required = false) String password,
                                    @RequestParam(required = false) String avatar,
                                    @RequestParam(required = false) String gender,
                                    @RequestParam(required = false) String telephone,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String zone) {
        int count = userService.modifyUserInfo(userId, nickName, password, avatar, gender, telephone, email, zone);
        if (count <= 0) {
            return Result.error("-1", "修改失败");
        } else {
            return Result.success();
        }
    }


}


package com.example.demo.controller;

import cn.hutool.core.lang.Assert;
import com.example.demo.entity.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @param
 * @author cj
 * @date 2022/6/18 17:35
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void login() {
        mockMvc.perform(get())
    }

    @Test
    void register() {
    }

}
package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    void UT_TC_001_001_001_1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "zqf")
                        .param("password", "")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_001_001_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "")
                        .param("password", "12345")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_001_3() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "zqf")
                        .param("password", "12345")
                        .param("role", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_002() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "mjy")
                        .param("password", "1234")
                        .param("role", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_003() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "lwy")
                        .param("password", "1234")
                        .param("role", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_004_1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "mjy")
                        .param("password", "12345")
                        .param("role", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_004_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "zqf")
                        .param("password", "123456")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_005() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "zqf")
                        .param("password", "12345")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_001_006() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("nickName", "lwy")
                        .param("password", "12345")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void UT_TC_001_002_001_1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .param("nickName", "")
                        .param("password", "12345")
                        .param("role", "0"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_002_001_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .param("nickName", "lwy1")
                        .param("password", "")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_002() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .param("nickName", "mjy")
                        .param("password", "12345")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_002_003() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .param("nickName", "lwy")
                        .param("password", "12345")
                        .param("role", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
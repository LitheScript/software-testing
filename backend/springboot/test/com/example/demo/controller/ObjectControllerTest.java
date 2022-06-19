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

@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ObjectControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    void UT_TC_001_003_001() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/getObjectDetail")
                        .param("objectId", ""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_003_002() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/getObjectDetail")
                        .param("objectId", "-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_003_003() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/getObjectDetail")
                        .param("objectId", "4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andReturn();
    }

    @Test
    void UT_TC_001_004_001() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/searchObject")
                        .param("name", "")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_004_002() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/searchObject")
                        .param("name", "adad")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_004_003() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/object/searchObject")
                        .param("name", "手表")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andReturn();
    }

    @Test
    void UT_TC_001_005_001_1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "4")
                        .param("objectId", ""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_005_001_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "4")
                        .param("objectId", ""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_005_001_3() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "")
                        .param("objectId", "4"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_005_002_1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "-1")
                        .param("objectId", "4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_005_002_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "4")
                        .param("objectId", "-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_005_002_3() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "-1")
                        .param("objectId", "-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_005_003() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "1")
                        .param("objectId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_005_004 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/fav/collect")
                        .param("favId", "1")
                        .param("objectId","23"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andReturn();
    }


    @Test
    void UT_TC_001_006_001_1 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "1")
                        .param("objectId",""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void UT_TC_001_006_001_2 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "")
                        .param("objectId","5"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void UT_TC_001_006_001_3 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "")
                        .param("objectId",""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void UT_TC_001_006_002_1 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "1")
                        .param("objectId","-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_006_002_2 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "-1")
                        .param("objectId","5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_006_003 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "2")
                        .param("objectId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(-1))
                .andReturn();
    }

    @Test
    void UT_TC_001_006_004 () throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fav/deleteCollection")
                        .param("favId", "1")
                        .param("objectId","7"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andReturn();
    }
}
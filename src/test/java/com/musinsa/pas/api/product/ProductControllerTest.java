package com.musinsa.pas.api.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("product select API")
    @Test
    void getProductTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/products")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .param("offset", "1").param("limit", "10"))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode resultNode = mapper.readTree(result.getResponse().getContentAsString());
        assertTrue("000000".equals(resultNode.get("status").asText()), "상품 조회 실패");
    }

    @DisplayName("product insert API")
    @Test
    void setProductTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "테스트 상품");
        map.put("content", "테스트 상품 설명");
        map.put("companyCode", "EE115");

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(map);

        MvcResult result = mockMvc.perform(post("/v1/products")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonBody))
                .andExpect(status().isCreated()).andReturn();
        JsonNode resultNode = mapper.readTree(result.getResponse().getContentAsString());
        assertTrue("000000".equals(resultNode.get("status").asText()), "상품 입력 실패");
    }

}
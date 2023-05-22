package com.productrank.api.domain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productrank.api.config.security.JwtTokenProvider;
import com.productrank.api.config.security.SecurityConfig;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.service.ProductService;
import com.productrank.api.domain.service.annotaion.WithAuthUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ContextConfiguration(classes = {SecurityConfig.class})
//@WebAppConfiguration
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @WithAuthUser(email = "test@naver.com", role="ROLE_ADMIN")
    void test() throws Exception {
        mockMvc.perform(get("/app/product/all"))
                .andDo(print());
    }

    @Test
    @WithAuthUser(email = "test@naver.com", role="ROLE_ADMIN")
    void add() throws Exception {

        ProductDto productDto = ProductDto.builder()
                .productName("test")
                .productDescription("desc")
                .company("1")
                .build();

        mockMvc.perform(post("/app/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
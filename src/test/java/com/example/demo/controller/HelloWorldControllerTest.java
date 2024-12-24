package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorldReturnsCorrectMessage() throws Exception {

        MvcResult result = mockMvc.perform(get("/hello-world"))
                .andReturn();

        assertEquals("Hello world!", result.getResponse().getContentAsString());

    }

    @Test
    void helloWorldMvc() throws Exception {
        mockMvc.perform(get("/hello-world?name=John"))
                .andExpect(content().string("Hello John!"));
    }
}
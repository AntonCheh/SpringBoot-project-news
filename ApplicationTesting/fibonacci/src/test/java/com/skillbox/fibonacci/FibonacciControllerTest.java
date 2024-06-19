package com.skillbox.fibonacci;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciService service;

    @Test
    void testGetFibonacciNumber() throws Exception {
        FibonacciNumber number = new FibonacciNumber(5, 5);
        when(service.fibonacciNumber(5)).thenReturn(number);

        mockMvc.perform(get("/fibonacci/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"index\":5,\"value\":5}"));
    }

    @Test
    void testGetFibonacciNumberInvalidIndex() throws Exception {
        when(service.fibonacciNumber(0)).thenThrow(new IllegalArgumentException("Index should be greater or equal to 1"));

        mockMvc.perform(get("/fibonacci/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Index should be greater or equal to 1"));
    }

}

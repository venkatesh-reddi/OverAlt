package com.overalt.exception;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerNotFoundException() throws Exception {
        mockMvc.perform(get("/some-endpoint")) // Use an endpoint that triggers CustomerNotFoundException
               .andExpect(status().isNotFound())
               .andExpect(content().string("Customer not found")); // Replace with the actual message
    }
}

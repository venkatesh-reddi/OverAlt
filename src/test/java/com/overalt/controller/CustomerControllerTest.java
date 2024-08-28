package com.overalt.controller;

import static org.assertj.core.api.Java6Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overalt.exception.customer.CustomerNotFoundException;
import com.overalt.exception.customer.InvalidCustomerDataException;
import com.overalt.model.Customer;
import com.overalt.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateCustomer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @SuppressWarnings("deprecation")
    @Test
    void testCreateCustomer_Invalid() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidCustomerDataException.class)
                        .hasMessage("Customer data is invalid");
                });
    }

    @Test
    void testGetCustomerById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.findByCustomerId(anyInt())).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @SuppressWarnings("deprecation")
    @Test
    void testGetCustomerById_NotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        when(customerRepository.findByCustomerId(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(CustomerNotFoundException.class)
                        .hasMessage("Customer with ID 1 not found");
                });
    }

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1);
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");
        existingCustomer.setEmail("john.doe@example.com");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Doe");
        updatedCustomer.setEmail("jane.doe@example.com");

        when(customerRepository.findByCustomerId(anyInt())).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @SuppressWarnings("deprecation")
    @Test
    void testUpdateCustomer_NotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Doe");
        updatedCustomer.setEmail("jane.doe@example.com");

        when(customerRepository.findByCustomerId(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(CustomerNotFoundException.class)
                        .hasMessage("Customer with ID 1 not found");
                });
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        doNothing().when(customerRepository).deleteById(anyInt());

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCustomer_InternalServerError() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        doThrow(new RuntimeException()).when(customerRepository).deleteById(anyInt());

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isInternalServerError());
    }
}

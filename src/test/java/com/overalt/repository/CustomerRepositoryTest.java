package com.overalt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.Customer;
import com.overalt.model.Plan;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    private Plan plan;

    @BeforeEach
    void setUp() {
        // Initialize test data
        plan = new Plan();
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setEmail("test@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setPlan(plan);
    }

    @Test
    void testFindByEmail() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerRepository.findByEmail("test@example.com");
        verify(customerRepository, times(1)).findByEmail("test@example.com");
        assertThat(result).isPresent().contains(customer);
    }

    @Test
    void testFindByPhoneNumber() {
        when(customerRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerRepository.findByPhoneNumber("1234567890");
        verify(customerRepository, times(1)).findByPhoneNumber("1234567890");
        assertThat(result).isPresent().contains(customer);
    }

    @Test
    void testFindByCustomerId() {
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerRepository.findByCustomerId(1);
        verify(customerRepository, times(1)).findByCustomerId(1);
        assertThat(result).isPresent().contains(customer);
    }

    @Test
    void testFindByPlan() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findByPlan(any(Plan.class))).thenReturn(customers);

        List<Customer> result = customerRepository.findByPlan(plan);
        verify(customerRepository, times(1)).findByPlan(plan);
        assertThat(result).hasSize(1).contains(customer);
    }
}

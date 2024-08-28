package com.overalt.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.Customer;
import com.overalt.model.Plan;
import com.overalt.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private Plan plan;

    @BeforeEach
    public void setUp() {
        plan = new Plan(1, "Basic Plan", 100, 50, BigDecimal.valueOf(29.99), 4, 5);
        customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", plan, 2, 3);
    }

    @Test
    public void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getCustomerId()).isEqualTo(1);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findByCustomerId(1)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getCustomerId()).isEqualTo(1);
        verify(customerRepository, times(1)).findByCustomerId(1);
    }

    @Test
    public void testGetCustomerByEmail() {
        when(customerRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerByEmail("john.doe@example.com");

        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getEmail()).isEqualTo("john.doe@example.com");
        verify(customerRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    public void testGetCustomerByPhoneNumber() {
        when(customerRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerByPhoneNumber("1234567890");

        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getPhoneNumber()).isEqualTo("1234567890");
        verify(customerRepository, times(1)).findByPhoneNumber("1234567890");
    }

    @Test
    public void testGetCustomersByPlan() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findByPlan(plan)).thenReturn(customers);

        List<Customer> foundCustomers = customerService.getCustomersByPlan(plan);

        assertThat(foundCustomers).isNotEmpty();
        assertThat(foundCustomers.size()).isEqualTo(1);
        verify(customerRepository, times(1)).findByPlan(plan);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> allCustomers = customerService.getAllCustomers();

        assertThat(allCustomers).isNotEmpty();
        assertThat(allCustomers.size()).isEqualTo(1);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomerById() {
        doNothing().when(customerRepository).deleteById(1);

        customerService.deleteCustomerById(1);

        verify(customerRepository, times(1)).deleteById(1);
    }
}

package com.overalt.service;

import com.overalt.model.Customer;
import com.overalt.model.Plan;
import com.overalt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findByCustomerId(customerId).orElse(null);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Customer> getCustomersByPlan(Plan plan) {
        return customerRepository.findByPlan(plan);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomerById(int customerId) {
        customerRepository.deleteById(customerId);
    }
}

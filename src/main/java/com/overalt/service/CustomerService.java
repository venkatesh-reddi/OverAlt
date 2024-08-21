package com.overalt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overalt.model.Customer;
import com.overalt.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Customer> getCustomerById(int customerId) {
        return customerRepository.findByCustomer_id(customerId);
    }

    public List<Customer> getCustomersByPlanId(int planId) {
        return customerRepository.findByPlan_id(planId);
    }
}

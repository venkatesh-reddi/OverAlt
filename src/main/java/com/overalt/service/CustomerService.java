package com.overalt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.overalt.model.Customer;
import com.overalt.repository.CustomerRepository;

public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Customer getCustomerById(int customer_id) {
        return customerRepository.findByCustomer_id(customer_id);
    }

    public List<Customer> getCustomersByPlanId(int plan_id) {
        return customerRepository.findByPlan_id(plan_id);
    }
}

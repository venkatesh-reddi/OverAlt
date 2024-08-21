package com.overalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.overalt.model.Customer;

@Service
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findByPhoneNumber(String phoneNumber);
    Customer findByCustomer_id(int customer_id);
    List<Customer> findByPlan_id(int plan_id);
}

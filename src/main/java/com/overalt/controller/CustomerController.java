package com.overalt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.model.Customer;
import com.overalt.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerByEmail")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getCustomerByPhoneNumber")
    public ResponseEntity<Customer> getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<Customer> customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getCustomerById")
    public ResponseEntity<Customer> getCustomerById(@RequestParam int customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        return customer.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getCustomersByPlanId")
    public ResponseEntity<List<Customer>> getCustomersByPlanId(@RequestParam int planId) {
        List<Customer> customers = customerService.getCustomersByPlanId(planId);
        return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
    }
}

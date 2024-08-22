package com.overalt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.model.Customer;
import com.overalt.model.Plan;
import com.overalt.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
    CustomerService customerService;

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
    public ResponseEntity<List<Customer>> getCustomersByPlanId(@RequestParam Plan plan) {
        List<Customer> customers = customerService.getCustomersByPlan(plan);
        return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
    }
}

package com.overalt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.service.CustomerService;


@RestController
public class CustomerController {
    @GetMapping("/getCustomerByEmail")
    public String getCustomerByEmail(@RequestParam String email) {
        CustomerService customerService = new CustomerService(null);
        return customerService.getCustomerByEmail(email).toString();
    }

    @GetMapping("/getCustomerByPhoneNumber")
    public String getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
        CustomerService customerService = new CustomerService(null);
        return customerService.getCustomerByPhoneNumber(phoneNumber).toString();
    }

    @GetMapping("/getCustomerById")
    public String getCustomerById(@RequestParam int customer_id) {
        CustomerService customerService = new CustomerService(null);
        return customerService.getCustomerById(customer_id).toString();
    }

    @GetMapping("/getCustomersByPlanId")
    public String getCustomersByPlanId(@RequestParam int plan_id) {
        CustomerService customerService = new CustomerService(null);
        return customerService.getCustomersByPlanId(plan_id).toString();
    }
}

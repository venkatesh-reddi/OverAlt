package com.overalt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.service.CustomerService;



@RestController
public class FriendOrFamilyController {
    @GetMapping("/updateContactNumber")
    public String getMethodName(@RequestParam String newPhoneNumber) {
       CustomerService FriendOrFamilyService = new CustomerService(null);
        return FriendOrFamilyService.getCustomerByEmail(newPhoneNumber).toString();
    }

    
}

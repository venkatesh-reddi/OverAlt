package com.overalt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.service.CustomerService;

@RestController
public class PlanController {
    @GetMapping("/getPlanByid")
    public String getPlanById(@RequestParam int plan_id) {
        
    }

    

    
}

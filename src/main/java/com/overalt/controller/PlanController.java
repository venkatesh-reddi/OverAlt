package com.overalt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.model.Plan;
import com.overalt.service.PlanService;

@RestController
public class PlanController {

    @GetMapping("/getPlanByid")
    public Plan getPlanByid(@RequestParam int plan_id) {
        PlanService planservice = new PlanService(null);
        return planservice.getPlanById(plan_id);
        
    }

    @GetMapping("/getPlanByname")
    public String getPlanByname(@RequestParam String plan_name) {
        PlanService planService = new PlanService(null);
        return planService.getPlanByName(plan_name).toString();
    }
}

package com.overalt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.model.Plan;
import com.overalt.service.PlanService;

import java.util.Optional;

@RestController
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/getPlanById")
    public ResponseEntity<Plan> getPlanById(@RequestParam int planId) {
        Optional<Plan> plan = planService.getPlanById(planId);
        return plan.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getPlanByName")
    public ResponseEntity<Plan> getPlanByName(@RequestParam String planName) {
        Optional<Plan> plan = planService.getPlanByName(planName);
        return plan.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

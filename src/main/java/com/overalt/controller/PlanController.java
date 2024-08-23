package com.overalt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    // Create a new plan
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan savedPlan = planRepository.save(plan);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    // Get a plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable int id) {
        Optional<Plan> plan = planRepository.findById(id);
        return plan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all plans
    @GetMapping
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    // Update an existing plan
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable int id, @RequestBody Plan planDetails) {
        Optional<Plan> planOptional = planRepository.findById(id);
        if (planOptional.isPresent()) {
            Plan plan = planOptional.get();
            plan.setPlanName(planDetails.getPlanName());
            plan.setMaxFamilyMembers(planDetails.getMaxFamilyMembers());
            plan.setMaxFriends(planDetails.getMaxFriends());
            Plan updatedPlan = planRepository.save(plan);
            return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a plan
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlan(@PathVariable int id) {
        try {
            planRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.overalt.service;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan getPlanById(int planId) {
        return planRepository.findByPlanId(planId).orElse(null);
    }

    public Optional<Plan> getPlanByName(String planName) {
        return planRepository.findByPlanName(planName);
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public void deletePlanById(int planId) {
        planRepository.deleteById(planId);
    }
}

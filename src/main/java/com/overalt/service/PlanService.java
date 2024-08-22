package com.overalt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;

    public Optional<Plan> getPlanById(int planId) {
        return planRepository.findByPlanId(planId);
    }

    public Optional<Plan> getPlanByName(String planName) {
        return planRepository.findByPlanName(planName);
    }
}

package com.overalt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Optional<Plan> getPlanById(int planId) {
        return planRepository.findByPlan_id(planId);
    }

    public Optional<Plan> getPlanByName(String planName) {
        return planRepository.findByPlan_name(planName);
    }
}

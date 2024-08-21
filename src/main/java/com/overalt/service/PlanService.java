package com.overalt.service;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

public class PlanService {

private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

public Plan getPlanById(int plan_id) {
        return planRepository.findByPlan_id(plan_id);
}

public Plan getPlanByName(String plan_name) {
    return planRepository.findByPlan_name(plan_name);
}

   
}
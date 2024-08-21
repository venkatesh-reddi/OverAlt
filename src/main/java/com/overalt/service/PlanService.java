package com.overalt.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.overalt.repository.PlanRepository;

public class PlanService {

@Autowired
private PlanRepository planRepository;

public Plan getPlanById(int plan_id) {
        return planRepository.findByPlan_id(plan_id);
}

public Plan getPlanByName(String plan_name) {
    return planRepository.findByPlan_name(plan_name);
}

   
}
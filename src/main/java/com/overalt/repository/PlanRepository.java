package com.overalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.overalt.model.Plan;


@Service
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    Plan findByPlan_id(int plan_id);
    Plan findByPlan_name(String plan_name);
}

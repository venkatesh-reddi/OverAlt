package com.overalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.overalt.model.Plans;


@Service
public interface PlanRepository extends JpaRepository<Plans, Integer> {
    Plans findByPlan_id(int plan_id);
    Plans findByPlan_name(String plan_name);
}

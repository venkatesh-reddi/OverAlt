package com.overalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.overalt.model.Plan;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    Optional<Plan> findByPlan_id(int planId);
    Optional<Plan> findByPlan_name(String planName);
}

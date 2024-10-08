package com.overalt.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTests {

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanService planService;

    private Plan plan;

    @BeforeEach
    public void setUp() {
        plan = new Plan(1, "Basic Plan", 500, 100, new BigDecimal("29.99"), 4, 5);
    }

    @Test
    public void testSavePlan() {
        when(planRepository.save(any(Plan.class))).thenReturn(plan);
        Plan savedPlan = planService.savePlan(plan);
        assertThat(savedPlan).isNotNull();
        assertThat(savedPlan.getPlanId()).isEqualTo(1);
        verify(planRepository, times(1)).save(plan);
    }

    @Test
    public void testGetPlanById() {
        when(planRepository.findByPlanId(1)).thenReturn(Optional.of(plan));
        Plan foundPlan = planService.getPlanById(1);
        assertThat(foundPlan).isNotNull();
        assertThat(foundPlan.getPlanId()).isEqualTo(1);
        verify(planRepository, times(1)).findByPlanId(1);
    }

    @Test
    public void testGetPlanByName() {
        when(planRepository.findByPlanName("Basic Plan")).thenReturn(Optional.of(plan));
        Optional<Plan> foundPlan = planService.getPlanByName("Basic Plan");
        assertThat(foundPlan).isPresent();
        assertThat(foundPlan.get().getPlanName()).isEqualTo("Basic Plan");
        verify(planRepository, times(1)).findByPlanName("Basic Plan");
    }

    @Test
    public void testGetAllPlans() {
        List<Plan> plans = Arrays.asList(plan);
        when(planRepository.findAll()).thenReturn(plans);
        List<Plan> allPlans = planService.getAllPlans();
        assertThat(allPlans).isNotEmpty();
        assertThat(allPlans.size()).isEqualTo(1);
        verify(planRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePlanById() {
        doNothing().when(planRepository).deleteById(1);
        planService.deletePlanById(1);
        verify(planRepository, times(1)).deleteById(1);
    }
}

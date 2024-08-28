package com.overalt.repository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.Plan;

@ExtendWith(MockitoExtension.class)
public class PlanRepositoryTest {

    @Mock
    private PlanRepository planRepository;

    private Plan plan;

    @BeforeEach
    void setUp() {
        // Initialize test data
        plan = new Plan();
        plan.setPlanId(1);
        plan.setPlanName("Premium");
    }

    @Test
    void testFindByPlanId() {
        when(planRepository.findByPlanId(anyInt())).thenReturn(Optional.of(plan));

        Optional<Plan> result = planRepository.findByPlanId(1);
        verify(planRepository, times(1)).findByPlanId(1);
        assertThat(result).isPresent().contains(plan);
    }

    @Test
    void testFindByPlanName() {
        when(planRepository.findByPlanName(anyString())).thenReturn(Optional.of(plan));

        Optional<Plan> result = planRepository.findByPlanName("Premium");
        verify(planRepository, times(1)).findByPlanName("Premium");
        assertThat(result).isPresent().contains(plan);
    }
}

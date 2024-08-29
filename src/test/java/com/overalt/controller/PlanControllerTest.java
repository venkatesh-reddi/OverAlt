package com.overalt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overalt.exception.plan.InvalidPlanDataException;
import com.overalt.exception.plan.PlanNotFoundException;
import com.overalt.model.Plan;
import com.overalt.repository.PlanRepository;

@ExtendWith(MockitoExtension.class)
public class PlanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanController planController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(planController).build();
    }

    @Test
    void testCreatePlan() throws Exception {
        Plan plan = new Plan();
        plan.setPlanId(1);
        plan.setPlanName("Basic Plan");
        plan.setMaxFamilyMembers(4);
        plan.setMaxFriends(10);

        when(planRepository.save(any(Plan.class))).thenReturn(plan);

        mockMvc.perform(post("/plans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plan)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.planId").value(1))
                .andExpect(jsonPath("$.planName").value("Basic Plan"))
                .andExpect(jsonPath("$.maxFamilyMembers").value(4))
                .andExpect(jsonPath("$.maxFriends").value(10));
    }

    @Test
    void testCreatePlan_Invalid() throws Exception {
        mockMvc.perform(post("/plans")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"planName\": \"\", \"maxFamilyMembers\": 4, \"maxFriends\": 10 }"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidPlanDataException.class)
                        .hasMessage("Plan data is invalid. Plan name cannot be null or empty.");
                });
    }

    @Test
    void testGetPlanById() throws Exception {
        Plan plan = new Plan();
        plan.setPlanId(4);
        plan.setPlanName("Basic Plan");
        plan.setMaxFamilyMembers(4);
        plan.setMaxFriends(10);

        when(planRepository.findById(anyInt())).thenReturn(Optional.of(plan));

        mockMvc.perform(get("/plans/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value(1))
                .andExpect(jsonPath("$.planName").value("Basic Plan"))
                .andExpect(jsonPath("$.maxFamilyMembers").value(4))
                .andExpect(jsonPath("$.maxFriends").value(10));
    }

    @Test
    void testGetPlanById_NotFound() throws Exception {
        when(planRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/plans/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(PlanNotFoundException.class)
                        .hasMessage("Plan with ID 4 not found");
                });
    }

    @Test
    void testGetAllPlans() throws Exception {
        Plan plan1 = new Plan();
        plan1.setPlanId(1);
        plan1.setPlanName("Basic Plan");
        plan1.setMaxFamilyMembers(4);
        plan1.setMaxFriends(10);

        Plan plan2 = new Plan();
        plan2.setPlanId(2);
        plan2.setPlanName("Premium Plan");
        plan2.setMaxFamilyMembers(6);
        plan2.setMaxFriends(20);

        List<Plan> plans = Arrays.asList(plan1, plan2);

        when(planRepository.findAll()).thenReturn(plans);

        mockMvc.perform(get("/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].planId").value(1))
                .andExpect(jsonPath("$[0].planName").value("Basic Plan"))
                .andExpect(jsonPath("$[1].planId").value(2))
                .andExpect(jsonPath("$[1].planName").value("Premium Plan"));
    }

    @Test
    void testUpdatePlan() throws Exception {
        Plan existingPlan = new Plan();
        existingPlan.setPlanId(1);
        existingPlan.setPlanName("Basic Plan");
        existingPlan.setMaxFamilyMembers(4);
        existingPlan.setMaxFriends(10);

        Plan updatedPlan = new Plan();
        updatedPlan.setPlanId(1);
        updatedPlan.setPlanName("Updated Plan");
        updatedPlan.setMaxFamilyMembers(5);
        updatedPlan.setMaxFriends(15);

        when(planRepository.findById(anyInt())).thenReturn(Optional.of(existingPlan));
        when(planRepository.save(any(Plan.class))).thenReturn(updatedPlan);

        mockMvc.perform(put("/plans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPlan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value(1))
                .andExpect(jsonPath("$.planName").value("Updated Plan"))
                .andExpect(jsonPath("$.maxFamilyMembers").value(5))
                .andExpect(jsonPath("$.maxFriends").value(15));
    }

    @Test
    void testUpdatePlan_NotFound() throws Exception {
        Plan updatedPlan = new Plan();
        updatedPlan.setPlanId(4);
        updatedPlan.setPlanName("Updated Plan");
        updatedPlan.setMaxFamilyMembers(5);
        updatedPlan.setMaxFriends(15);

        when(planRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(put("/plans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPlan)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(PlanNotFoundException.class)
                        .hasMessage("Plan with ID 4 not found");
                });
    }

    @Test
    void testDeletePlan() throws Exception {
        doNothing().when(planRepository).deleteById(anyInt());

        mockMvc.perform(delete("/plans/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePlan_InternalServerError() throws Exception {
        doThrow(new RuntimeException()).when(planRepository).deleteById(anyInt());

        mockMvc.perform(delete("/plans/1"))
                .andExpect(status().isInternalServerError());
    }
}

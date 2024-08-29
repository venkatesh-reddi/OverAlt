package com.overalt.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overalt.exception.calldetails.CallDetailsNotFoundException;
import com.overalt.exception.calldetails.InvalidCallDetailsException;
import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class CallDetailsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CallDetailsRepository callDetailsRepository;

    @InjectMocks
    private CallDetailsController callDetailsController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(callDetailsController).build();
    }

    @Test
    void testCreateCallDetail() throws Exception {
        // Prepare test data
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        when(callDetailsRepository.save(any(CallDetails.class))).thenReturn(callDetails);

        mockMvc.perform(post("/calldetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(callDetails)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.callId").value(1))
                .andExpect(jsonPath("$.callerId").value(123))
                .andExpect(jsonPath("$.receiverId").value(456));
    }

    @Test
    void testCreateCallDetail_Invalid() throws Exception {
        mockMvc.perform(post("/calldetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidCallDetailsException.class)
                        .hasMessage("Call details data is invalid");
                });
    }

    @Test
    void testGetCallDetailById() throws Exception {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        when(callDetailsRepository.findById(anyInt())).thenReturn(Optional.of(callDetails));

        mockMvc.perform(get("/calldetails/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.callId").value(1))
                .andExpect(jsonPath("$.callerId").value(123))
                .andExpect(jsonPath("$.receiverId").value(456));
    }

    @Test
    void testGetCallDetailById_NotFound() throws Exception {
        when(callDetailsRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/calldetails/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(CallDetailsNotFoundException.class)
                        .hasMessage("Call Details with id 1 not found");
                });
    }

    @Test
    void testGetAllCallDetails() throws Exception {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        List<CallDetails> callDetailsList = Arrays.asList(callDetails);
        when(callDetailsRepository.findAll()).thenReturn(callDetailsList);

        mockMvc.perform(get("/calldetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].callId").value(1))
                .andExpect(jsonPath("$[0].callerId").value(123))
                .andExpect(jsonPath("$[0].receiverId").value(456));
    }

    @Test
    void testGetCallDetailsByCallerId() throws Exception {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        List<CallDetails> callDetailsList = Arrays.asList(callDetails);
        when(callDetailsRepository.findByCallerId(anyLong())).thenReturn(callDetailsList);

        mockMvc.perform(get("/calldetails/caller/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].callId").value(1))
                .andExpect(jsonPath("$[0].callerId").value(123))
                .andExpect(jsonPath("$[0].receiverId").value(456));
    }

    @Test
    void testGetCallDetailsByReceiverId() throws Exception {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        List<CallDetails> callDetailsList = Arrays.asList(callDetails);
        when(callDetailsRepository.findByReceiverId(anyLong())).thenReturn(callDetailsList);

        mockMvc.perform(get("/calldetails/receiver/456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].callId").value(1))
                .andExpect(jsonPath("$[0].callerId").value(123))
                .andExpect(jsonPath("$[0].receiverId").value(456));
    }

    @Test
    void testUpdateCallDetail() throws Exception {
        CallDetails existingCallDetails = new CallDetails();
        existingCallDetails.setCallId(1);
        existingCallDetails.setCallerId(123L);
        existingCallDetails.setReceiverId(456L);
        existingCallDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        existingCallDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        CallDetails updatedDetails = new CallDetails();
        updatedDetails.setCallId(1); // Ensure ID is set for update
        updatedDetails.setCallerId(123L);
        updatedDetails.setReceiverId(456L);
        updatedDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        updatedDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 45));

        when(callDetailsRepository.findById(anyInt())).thenReturn(Optional.of(existingCallDetails));
        when(callDetailsRepository.save(any(CallDetails.class))).thenReturn(updatedDetails);

        mockMvc.perform(put("/calldetails/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.callEndTime").value("2024-01-01T10:45:00"));
    }

    @Test
    void testUpdateCallDetail_NotFound() throws Exception {
        CallDetails updatedDetails = new CallDetails();
        updatedDetails.setCallerId(123L);
        updatedDetails.setReceiverId(456L);
        updatedDetails.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        updatedDetails.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 45));

        when(callDetailsRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(put("/calldetails/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(CallDetailsNotFoundException.class)
                        .hasMessage("Call Details with id 1 not found");
                });
    }

    @Test
    void testDeleteCallDetail() throws Exception {
        doNothing().when(callDetailsRepository).deleteById(anyInt());

        mockMvc.perform(delete("/calldetails/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCallDetail_InternalServerError() throws Exception {
        doThrow(new RuntimeException()).when(callDetailsRepository).deleteById(anyInt());

        mockMvc.perform(delete("/calldetails/1"))
                .andExpect(status().isInternalServerError());
    }
}
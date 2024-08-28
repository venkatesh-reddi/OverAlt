package com.overalt.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class CallDetailsServiceTest {

    @InjectMocks
    private CallDetailsService callDetailsService;

    @Mock
    private CallDetailsRepository callDetailsRepository;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this); // No longer needed with @ExtendWith(MockitoExtension.class)
    }

    @Test
    public void testSaveCallDetails() {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        callDetails.setCallStartTime(LocalDateTime.now().minusMinutes(10));
        callDetails.setCallEndTime(LocalDateTime.now());
        callDetails.calculateCallDuration();
        
        when(callDetailsRepository.save(any(CallDetails.class))).thenReturn(callDetails);

        CallDetails savedCallDetails = callDetailsService.saveCallDetails(callDetails);
        assertThat(savedCallDetails).isNotNull();
        assertThat(savedCallDetails.getCallId()).isEqualTo(1);
        assertThat(savedCallDetails.getCallDuration()).isGreaterThan(0);
    }

    @Test
    public void testGetCallDetailsById() {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallId(1);
        when(callDetailsRepository.findById(1)).thenReturn(Optional.of(callDetails));

        CallDetails foundCallDetails = callDetailsService.getCallDetailsById(1);
        assertThat(foundCallDetails).isNotNull();
        assertThat(foundCallDetails.getCallId()).isEqualTo(1);
    }

    @Test
    public void testGetAllCallDetails() {
        CallDetails callDetails1 = new CallDetails();
        CallDetails callDetails2 = new CallDetails();
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails1);
        callDetailsList.add(callDetails2);

        when(callDetailsRepository.findAll()).thenReturn(callDetailsList);

        List<CallDetails> allCallDetails = callDetailsService.getAllCallDetails();
        assertThat(allCallDetails).hasSize(2);
    }

    @Test
    public void testGetCallDetailsByCallerId() {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallerId(123L);
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails);

        when(callDetailsRepository.findByCallerId(123L)).thenReturn(callDetailsList);

        List<CallDetails> foundCallDetails = callDetailsService.getCallDetailsByCallerId(123L);
        assertThat(foundCallDetails).hasSize(1);
        assertThat(foundCallDetails.get(0).getCallerId()).isEqualTo(123L);
    }

    @Test
    public void testGetCallDetailsByReceiverId() {
        CallDetails callDetails = new CallDetails();
        callDetails.setReceiverId(456L);
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails);

        when(callDetailsRepository.findByReceiverId(456L)).thenReturn(callDetailsList);

        List<CallDetails> foundCallDetails = callDetailsService.getCallDetailsByReceiverId(456L);
        assertThat(foundCallDetails).hasSize(1);
        assertThat(foundCallDetails.get(0).getReceiverId()).isEqualTo(456L);
    }

    @Test
    public void testGetCallDetailsByCallerAndReceiverId() {
        CallDetails callDetails = new CallDetails();
        callDetails.setCallerId(123L);
        callDetails.setReceiverId(456L);
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails);

        when(callDetailsRepository.findByCallerIdAndReceiverId(123L, 456L)).thenReturn(callDetailsList);

        List<CallDetails> foundCallDetails = callDetailsService.getCallDetailsByCallerAndReceiverId(123L, 456L);
        assertThat(foundCallDetails).hasSize(1);
        assertThat(foundCallDetails.get(0).getCallerId()).isEqualTo(123L);
        assertThat(foundCallDetails.get(0).getReceiverId()).isEqualTo(456L);
    }

    @Test
    public void testDeleteCallDetailsById() {
        doNothing().when(callDetailsRepository).deleteById(1);

        callDetailsService.deleteCallDetailsById(1);
        verify(callDetailsRepository, times(1)).deleteById(1);
    }
}

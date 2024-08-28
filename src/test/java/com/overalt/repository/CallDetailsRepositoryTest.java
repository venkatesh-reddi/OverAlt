package com.overalt.repository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.CallDetails;

@ExtendWith(MockitoExtension.class)
public class CallDetailsRepositoryTest {

    @Mock
    private CallDetailsRepository callDetailsRepository;

    private CallDetails callDetails1;
    private CallDetails callDetails2;

    @BeforeEach
    void setUp() {
        callDetails1 = new CallDetails();
        callDetails1.setCallerId(123L);
        callDetails1.setReceiverId(456L);
        callDetails1.setCallStartTime(LocalDateTime.of(2024, 1, 1, 10, 0));
        callDetails1.setCallEndTime(LocalDateTime.of(2024, 1, 1, 10, 30));

        callDetails2 = new CallDetails();
        callDetails2.setCallerId(123L);
        callDetails2.setReceiverId(789L);
        callDetails2.setCallStartTime(LocalDateTime.of(2024, 1, 2, 14, 0));
        callDetails2.setCallEndTime(LocalDateTime.of(2024, 1, 2, 14, 45));
    }

    @Test
    void testFindByCallerId() {
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails1);
        callDetailsList.add(callDetails2);

        when(callDetailsRepository.findByCallerId(anyLong())).thenReturn(callDetailsList);

        List<CallDetails> result = callDetailsRepository.findByCallerId(123L);
        verify(callDetailsRepository, times(1)).findByCallerId(123L);
        assertThat(result).hasSize(2).contains(callDetails1, callDetails2);
    }

    @Test
    void testFindByReceiverId() {
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails1);

        when(callDetailsRepository.findByReceiverId(anyLong())).thenReturn(callDetailsList);

        List<CallDetails> result = callDetailsRepository.findByReceiverId(456L);
        verify(callDetailsRepository, times(1)).findByReceiverId(456L);
        assertThat(result).hasSize(1).contains(callDetails1);
    }

    @Test
    void testFindByCallerIdAndReceiverId() {
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails1);

        when(callDetailsRepository.findByCallerIdAndReceiverId(anyLong(), anyLong())).thenReturn(callDetailsList);

        List<CallDetails> result = callDetailsRepository.findByCallerIdAndReceiverId(123L, 456L);
        verify(callDetailsRepository, times(1)).findByCallerIdAndReceiverId(123L, 456L);
        assertThat(result).hasSize(1).contains(callDetails1);
    }

    @Test
    void testFindByCallStartTimeBetween() {
        List<CallDetails> callDetailsList = new ArrayList<>();
        callDetailsList.add(callDetails1);
        callDetailsList.add(callDetails2);

        when(callDetailsRepository.findByCallStartTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
            .thenReturn(callDetailsList);

        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 2, 0, 0);
        List<CallDetails> result = callDetailsRepository.findByCallStartTimeBetween(startTime, endTime);
        verify(callDetailsRepository, times(1)).findByCallStartTimeBetween(startTime, endTime);
        assertThat(result).hasSize(2).contains(callDetails1, callDetails2);
    }

    @Test
    void testDeleteByCallId() {
        doNothing().when(callDetailsRepository).deleteByCallId(anyLong());

        callDetailsRepository.deleteByCallId(123L);
        verify(callDetailsRepository, times(1)).deleteByCallId(123L);
    }

    @Test
    void testDeleteByReceiverId() {
        doNothing().when(callDetailsRepository).deleteByReceiverId(anyLong());

        callDetailsRepository.deleteByReceiverId(456L);
        verify(callDetailsRepository, times(1)).deleteByReceiverId(456L);
    }
}

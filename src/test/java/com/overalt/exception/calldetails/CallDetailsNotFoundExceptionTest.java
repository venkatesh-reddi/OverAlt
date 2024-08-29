package com.overalt.exception.calldetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;
import com.overalt.service.CallDetailsService;

public class CallDetailsNotFoundExceptionTest {

    @Mock
    private CallDetailsRepository callDetailsRepository;

    @InjectMocks
    private CallDetailsService callDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCallDetailsByIdThrowsException() {
        // Arrange
        int testId = 123;
        when(callDetailsRepository.findById(testId)).thenReturn(Optional.empty());

        // Act & Assert
        CallDetailsNotFoundException thrownException = assertThrows(CallDetailsNotFoundException.class, () -> {
            callDetailsService.getCallDetailsById(testId);
        });

        // Assert
        assertEquals("Call details not found with ID: " + testId, thrownException.getMessage());
    }

    @Test
    public void testGetCallDetailsByIdReturnsCallDetails() {
        // Arrange
        int testId = 123;
        CallDetails expectedCallDetails = new CallDetails();
        when(callDetailsRepository.findById(testId)).thenReturn(Optional.of(expectedCallDetails));

        // Act
        CallDetails actualCallDetails = callDetailsService.getCallDetailsById(testId);

        // Assert
        assertEquals(expectedCallDetails, actualCallDetails);
    }
}

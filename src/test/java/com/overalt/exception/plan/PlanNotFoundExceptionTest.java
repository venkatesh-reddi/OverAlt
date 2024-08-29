package com.overalt.exception.plan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlanNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the ID for the test
        int testId = 101;

        // Create the exception with the test ID
        PlanNotFoundException exception = new PlanNotFoundException(testId);

        // Define the expected message
        String expectedMessage = "Plan not found with ID: " + testId;

        // Verify that the exception message matches the expected message
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the ID for the test
        int testId = 202;

        // Verify that the exception is thrown with the expected message
        PlanNotFoundException thrownException = assertThrows(
            PlanNotFoundException.class,
            () -> {
                throw new PlanNotFoundException(testId);
            }
        );

        // Define the expected message
        String expectedMessage = "Plan not found with ID: " + testId;

        // Verify that the message of the thrown exception is correct
        assertEquals(expectedMessage, thrownException.getMessage());
    }
}

package com.overalt.exception.plan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InvalidPlanDataExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the message for the test
        String testMessage = "Invalid plan data";

        // Create the exception with the test message
        InvalidPlanDataException exception = new InvalidPlanDataException(testMessage);

        // Verify that the exception message matches the expected message
        assertEquals(testMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the message for the test
        String testMessage = "Plan data is incorrect";

        // Verify that the exception is thrown with the expected message
        InvalidPlanDataException thrownException = assertThrows(
            InvalidPlanDataException.class,
            () -> {
                throw new InvalidPlanDataException(testMessage);
            }
        );

        // Verify that the message of the thrown exception is correct
        assertEquals(testMessage, thrownException.getMessage());
    }
}

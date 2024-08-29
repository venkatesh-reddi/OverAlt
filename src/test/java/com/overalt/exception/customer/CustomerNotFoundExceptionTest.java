package com.overalt.exception.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the ID for the test
        int testId = 123;
        
        // Create the exception with the test ID
        CustomerNotFoundException exception = new CustomerNotFoundException(testId);

        // Define the expected message
        String expectedMessage = "Customer not found with ID: " + testId;

        // Verify that the exception message matches the expected message
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the ID for the test
        int testId = 456;

        // Verify that the exception is thrown with the expected message
        CustomerNotFoundException thrownException = assertThrows(
            CustomerNotFoundException.class,
            () -> {
                throw new CustomerNotFoundException(testId);
            }
        );

        // Define the expected message
        String expectedMessage = "Customer not found with ID: " + testId;

        // Verify that the message of the thrown exception is correct
        assertEquals(expectedMessage, thrownException.getMessage());
    }
}

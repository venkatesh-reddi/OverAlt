package com.overalt.exception.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InvalidCustomerDataExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the message for the test
        String testMessage = "Invalid customer data";

        // Create the exception with the test message
        InvalidCustomerDataException exception = new InvalidCustomerDataException(testMessage);

        // Verify that the exception message matches the expected message
        assertEquals(testMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the message for the test
        String testMessage = "Customer data is incorrect";

        // Verify that the exception is thrown with the expected message
        InvalidCustomerDataException thrownException = assertThrows(
            InvalidCustomerDataException.class,
            () -> {
                throw new InvalidCustomerDataException(testMessage);
            }
        );

        // Verify that the message of the thrown exception is correct
        assertEquals(testMessage, thrownException.getMessage());
    }
}

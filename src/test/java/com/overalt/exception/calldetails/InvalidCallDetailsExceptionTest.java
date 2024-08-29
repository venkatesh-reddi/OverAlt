package com.overalt.exception.calldetails;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InvalidCallDetailsExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the expected message
        String expectedMessage = "Invalid call details";

        // Create the exception with the expected message
        InvalidCallDetailsException exception = new InvalidCallDetailsException(expectedMessage);

        // Verify that the exception message matches the expected message
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the expected message
        String expectedMessage = "Something went wrong";

        // Verify that the exception is thrown with the expected message
        InvalidCallDetailsException thrownException = assertThrows(
            InvalidCallDetailsException.class,
            () -> {
                throw new InvalidCallDetailsException(expectedMessage);
            }
        );

        // Verify that the message of the thrown exception is correct
        assertEquals(expectedMessage, thrownException.getMessage());
    }
}

package com.overalt.exception.friendorfamily;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InvalidFriendOrFamilyDataExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Define the message for the test
        String testMessage = "Invalid data for friend or family";

        // Create the exception with the test message
        InvalidFriendOrFamilyDataException exception = new InvalidFriendOrFamilyDataException(testMessage);

        // Verify that the exception message matches the expected message
        assertEquals(testMessage, exception.getMessage());
    }

    @Test
    public void testExceptionThrown() {
        // Define the message for the test
        String testMessage = "Friend or family data is invalid";

        // Verify that the exception is thrown with the expected message
        InvalidFriendOrFamilyDataException thrownException = assertThrows(
            InvalidFriendOrFamilyDataException.class,
            () -> {
                throw new InvalidFriendOrFamilyDataException(testMessage);
            }
        );

        // Verify that the message of the thrown exception is correct
        assertEquals(testMessage, thrownException.getMessage());
    }
}

package com.overalt.exception.friendorfamily;

public class FriendOrFamilyNotFoundException extends RuntimeException {
    public FriendOrFamilyNotFoundException(long contactNumber) {
        super("Friend or Family member not found with contact number: " + contactNumber);
    }
}

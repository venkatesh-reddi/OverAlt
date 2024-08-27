package com.overalt.exception.calldetails;

public class CallDetailsNotFoundException extends RuntimeException {
    public CallDetailsNotFoundException(int id) {
        super("Call details not found with ID: " + id);
    }
}

package com.overalt.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class CallDetails {
 // Attributes
    private int callId;
    private int customerId;
    private String calledNumber;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private double callDuration; // duration in minutes
    private String callType; // e.g., "local", "international"
    private boolean isAllowed;
    double callMinutes=3.00;

    // Constructor
    public CallDetails(int callId, int customerId, String calledNumber, LocalDateTime callStartTime, LocalDateTime callEndTime, String callType) {
        this.callId = callId;
        this.customerId = customerId;
        this.calledNumber = calledNumber;
        this.callStartTime = callStartTime;
        this.callEndTime = callEndTime;
        this.callType = callType;
        this.callDuration = calculateCallDuration();
    }

    // Method to calculate the call duration in minutes and seconds
    private double calculateCallDuration() {
        Duration duration = Duration.between(callStartTime, callEndTime);
        long seconds = duration.getSeconds();
        double minutes = seconds / 60.0;
        return minutes;
    }


    // Method to set whether the call is allowed based on the plan
    public void setIsAllowed(boolean isAllowed, int planId, double callMinutes) {
        // Compare callDuration with callMinutes from the Plan class
        if (callDuration <= callMinutes) {
            this.isAllowed = true;
        } else {
            this.isAllowed = false;
        }
    }

    // Getters and Setters for attributes
    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public LocalDateTime getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(LocalDateTime callStartTime) {
        this.callStartTime = callStartTime;
    }

    public LocalDateTime getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(LocalDateTime callEndTime) {
        this.callEndTime = callEndTime;
    }

    public double getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(double callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean isAllowed) {
        this.isAllowed = isAllowed;
    }
    //trying to test
    public static void main(String[] args) {
        // Example values
        int callId = 101;
        int customerId = 5001;
        String calledNumber = "+1234567890";
        LocalDateTime callStartTime = LocalDateTime.of(2024, 8, 15, 14, 30, 0); // August 15, 2024, 14:30:00
        LocalDateTime callEndTime = LocalDateTime.of(2024, 8, 15, 14, 45, 30);   // August 15, 2024, 14:45:30
        String callType = "local";

        // Create a CallDetails object
        CallDetails callDetails = new CallDetails(callId, customerId, calledNumber, callStartTime, callEndTime, callType);
        System.out.println(callDetails.calculateCallDuration());
        System.out.println(callDetails.isAllowed());
    }
}
//test sucessfull for call duration and to check if call is allowed



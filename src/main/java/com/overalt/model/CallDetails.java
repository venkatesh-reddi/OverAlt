package com.overalt.model;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table
public class CallDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer callId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "caller_id", nullable = false)
    private Long callerId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "call_start_time", nullable = false)
    private LocalDateTime callStartTime;

    @Column(name = "call_end_time", nullable = false)
    private LocalDateTime callEndTime;

    @Column(name = "call_duration", nullable = false)
    private Long callDuration; // In minutes

    // Getters and Setters

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public Long getCallerId() {
        return callerId;
    }

    public void setCallerId(Long callerId) {
        this.callerId = callerId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    public Long getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Long callDuration) {
        this.callDuration = callDuration;
    }

    // Method to calculate call duration
    public void calculateCallDuration() {
        if (callStartTime != null && callEndTime != null) {
            this.callDuration = Duration.between(callStartTime, callEndTime).toMinutes();
        }
    }
}

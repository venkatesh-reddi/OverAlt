package com.overalt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;

@Service
public class CallDetailsService {
    
    @Autowired
    CallDetailsRepository callDetailsRepository;

    public CallDetails saveCallDetails(CallDetails callDetails) {
        callDetails.calculateCallDuration();
        return callDetailsRepository.save(callDetails);
    }

    public Optional<CallDetails> getCallDetailsById(Integer callId) {
        return callDetailsRepository.findById(callId);
    }

    public List<CallDetails> getCallDetailsByCallerId(Long callerId) {
        return callDetailsRepository.findByCallerId(callerId);
    }
}

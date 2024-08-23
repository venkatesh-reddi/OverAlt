package com.overalt.service;

import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallDetailsService {

    @Autowired
    private CallDetailsRepository callDetailsRepository;

    public CallDetails saveCallDetails(CallDetails callDetails) {
        callDetails.calculateCallDuration(); // Ensure call duration is calculated before saving
        return callDetailsRepository.save(callDetails);
    }

    public CallDetails getCallDetailsById(Integer callId) {
        return callDetailsRepository.findById(callId).orElse(null);
    }

    public List<CallDetails> getAllCallDetails() {
        return callDetailsRepository.findAll();
    }

    public List<CallDetails> getCallDetailsByCallerId(Long callerId) {
        return callDetailsRepository.findByCallerId(callerId);
    }

    public List<CallDetails> getCallDetailsByReceiverId(Long receiverId) {
        return callDetailsRepository.findByReceiverId(receiverId);
    }

    public List<CallDetails> getCallDetailsByCallerAndReceiverId(Long callerId, Long receiverId) {
        return callDetailsRepository.findByCallerIdAndReceiverId(callerId, receiverId);
    }

    public void deleteCallDetailsById(Integer callId) {
        callDetailsRepository.deleteById(callId);
    }
}

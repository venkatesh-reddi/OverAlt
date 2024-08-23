package com.overalt.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.overalt.model.CallDetails;
import com.overalt.repository.CallDetailsRepository;

@RestController
@RequestMapping("/calldetails")
public class CallDetailsController {

    @Autowired
    private CallDetailsRepository callDetailsRepository;

    // Create a new call detail record
    @PostMapping
    public ResponseEntity<CallDetails> createCallDetail(@RequestBody CallDetails callDetails) {
        callDetails.calculateCallDuration();
        CallDetails savedCallDetails = callDetailsRepository.save(callDetails);
        return new ResponseEntity<>(savedCallDetails, HttpStatus.CREATED);
    }

    // Get a call detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<CallDetails> getCallDetailById(@PathVariable int id) {
        Optional<CallDetails> callDetails = callDetailsRepository.findById(id);
        return callDetails.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all call details
    @GetMapping
    public List<CallDetails> getAllCallDetails() {
        return callDetailsRepository.findAll();
    }

    // Get call details by caller ID
    @GetMapping("/caller/{callerId}")
    public List<CallDetails> getCallDetailsByCallerId(@PathVariable Long callerId) {
        return callDetailsRepository.findByCallerId(callerId);
    }

    // Get call details by receiver ID
    @GetMapping("/receiver/{receiverId}")
    public List<CallDetails> getCallDetailsByReceiverId(@PathVariable Long receiverId) {
        return callDetailsRepository.findByReceiverId(receiverId);
    }

    // Update an existing call detail
    @PutMapping("/{id}")
    public ResponseEntity<CallDetails> updateCallDetail(@PathVariable int id, @RequestBody CallDetails callDetailsDetails) {
        Optional<CallDetails> callDetailsOptional = callDetailsRepository.findById(id);
        if (callDetailsOptional.isPresent()) {
            CallDetails callDetails = callDetailsOptional.get();
            callDetails.setCallerId(callDetailsDetails.getCallerId());
            callDetails.setReceiverId(callDetailsDetails.getReceiverId());
            callDetails.setCallStartTime(callDetailsDetails.getCallStartTime());
            callDetails.setCallEndTime(callDetailsDetails.getCallEndTime());
            callDetails.calculateCallDuration();
            CallDetails updatedCallDetails = callDetailsRepository.save(callDetails);
            return new ResponseEntity<>(updatedCallDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a call detail record
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCallDetail(@PathVariable int id) {
        try {
            callDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

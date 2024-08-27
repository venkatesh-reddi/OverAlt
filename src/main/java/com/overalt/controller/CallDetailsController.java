package com.overalt.controller;

//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.exception.calldetails.CallDetailsNotFoundException;
import com.overalt.exception.calldetails.InvalidCallDetailsException;
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
        if(callDetails == null || callDetails.getCallerId() == null || callDetails.getReceiverId() == null){
            throw new InvalidCallDetailsException("Call details data is invalid");
        }
        callDetails.calculateCallDuration();
        CallDetails savedCallDetails = callDetailsRepository.save(callDetails);
        return new ResponseEntity<>(savedCallDetails, HttpStatus.CREATED);
    }

    // Get a call detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<CallDetails> getCallDetailById(@PathVariable int id) {
       CallDetails callDetails = callDetailsRepository.findById(id).orElseThrow(() -> new CallDetailsNotFoundException(id));
        return new ResponseEntity<>(callDetails, HttpStatus.OK);
        // callDetails.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        //                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        CallDetails callDetails = callDetailsRepository.findById(id).orElseThrow(() -> new CallDetailsNotFoundException(id));
        // if (callDetailsOptional.isPresent()) {
        //     CallDetails callDetails = callDetails.get();
            callDetails.setCallerId(callDetailsDetails.getCallerId());
            callDetails.setReceiverId(callDetailsDetails.getReceiverId());
            callDetails.setCallStartTime(callDetailsDetails.getCallStartTime());
            callDetails.setCallEndTime(callDetailsDetails.getCallEndTime());
            callDetails.calculateCallDuration();
            CallDetails updatedCallDetails = callDetailsRepository.save(callDetails);
            return new ResponseEntity<>(updatedCallDetails, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
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

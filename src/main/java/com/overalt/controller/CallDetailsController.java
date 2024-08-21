package com.overalt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.model.CallDetails;
import com.overalt.service.CallDetailsService;

@RestController
@RequestMapping("/api/calls")
public class CallDetailsController {

    @Autowired
    private CallDetailsService callDetailsService;

    @PostMapping
    public ResponseEntity<CallDetails> addCallDetails(@RequestBody CallDetails callDetails) {
        CallDetails savedCallDetails = callDetailsService.saveCallDetails(callDetails);
        return ResponseEntity.ok(savedCallDetails);
    }

    @GetMapping("/{callId}")
    public ResponseEntity<CallDetails> getCallDetailsById(@PathVariable Integer callId) {
        Optional<CallDetails> callDetails = callDetailsService.getCallDetailsById(callId);
        return callDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/caller/{callerId}")
    public ResponseEntity<List<CallDetails>> getCallDetailsByCallerId(@PathVariable Long callerId) {
        List<CallDetails> callDetails = callDetailsService.getCallDetailsByCallerId(callerId);
        return ResponseEntity.ok(callDetails);
    }
}

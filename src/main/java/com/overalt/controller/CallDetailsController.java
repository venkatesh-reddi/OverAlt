package com.overalt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.overalt.model.CallDetails;
import com.overalt.service.CallDetailsService;

@RestController
@RequestMapping("/calls")
public class CallDetailsController {

    private final CallDetailsService callDetailsService;

    // Constructor-based dependency injection
    public CallDetailsController(CallDetailsService callDetailsService) {
        this.callDetailsService = callDetailsService;
    }

    @PostMapping
    public ResponseEntity<CallDetails> addCallDetails(@RequestBody CallDetails callDetails) {
        CallDetails savedCallDetails = callDetailsService.saveCallDetails(callDetails);
        return ResponseEntity.ok(savedCallDetails);
    }

    @GetMapping("/{callId}")
    public ResponseEntity<CallDetails> getCallDetailsById(@PathVariable Integer callId) {
        Optional<CallDetails> callDetails = callDetailsService.getCallDetailsById(callId);
        return callDetails.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/caller/{callerId}")
    public ResponseEntity<List<CallDetails>> getCallDetailsByCallerId(@PathVariable Long callerId) {
        List<CallDetails> callDetailsList = callDetailsService.getCallDetailsByCallerId(callerId);
        return ResponseEntity.ok(callDetailsList);
    }
}

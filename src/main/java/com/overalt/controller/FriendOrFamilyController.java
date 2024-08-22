package com.overalt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overalt.service.FriendOrFamilyService;

@RestController
public class FriendOrFamilyController {

    @Autowired
    FriendOrFamilyService friendOrFamilyService;


    @PostMapping("/updateContactNumber")
    public String updateContactNumber(@RequestParam long oldContactNumber, @RequestParam long newContactNumber) {
        boolean updated = friendOrFamilyService.updateContactNumber(oldContactNumber, newContactNumber);
        return updated ? "Contact number updated successfully" : "Failed to update contact number";
    }
}

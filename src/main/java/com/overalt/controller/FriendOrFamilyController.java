package com.overalt.controller;

import java.util.List;
import java.util.Optional;

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

import com.overalt.exception.friendorfamily.FriendOrFamilyNotFoundException;
import com.overalt.exception.friendorfamily.InvalidFriendOrFamilyDataException;
import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;
import com.overalt.repository.CustomerRepository;
import com.overalt.repository.FriendOrFamilyRepository;

@RestController
@RequestMapping("/friendorfamily")
public class FriendOrFamilyController {

    @Autowired
    private FriendOrFamilyRepository friendOrFamilyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new friend or family member
    @PostMapping
    public ResponseEntity<FriendOrFamily> createFriendOrFamily(@RequestBody FriendOrFamily friendOrFamily) {
        if (friendOrFamily == null || friendOrFamily.getContactNumber() <= 0) {
            throw new InvalidFriendOrFamilyDataException("Friend or Family data is invalid. Contact number must be positive.");
        }
        FriendOrFamily savedFriendOrFamily = friendOrFamilyRepository.save(friendOrFamily);
        return new ResponseEntity<>(savedFriendOrFamily, HttpStatus.CREATED);
    }

    // Get a friend or family member by contact number
    @GetMapping("/{contactNumber}")
    public ResponseEntity<FriendOrFamily> getFriendOrFamilyByContactNumber(@PathVariable long contactNumber) {
        FriendOrFamily friendOrFamily = friendOrFamilyRepository.findByContactNumber(contactNumber);
        if (friendOrFamily != null) {
            return new ResponseEntity<>(friendOrFamily, HttpStatus.OK);
        } else {
            throw new FriendOrFamilyNotFoundException(contactNumber);
        }
    }

    // Get all friends or family members for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FriendOrFamily>> getFriendsOrFamilyByCustomerId(@PathVariable int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            List<FriendOrFamily> friendsOrFamily = friendOrFamilyRepository.findByCustomer(customer.get());
            return new ResponseEntity<>(friendsOrFamily, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing friend or family member
    @PutMapping("/{contactNumber}")
    public ResponseEntity<FriendOrFamily> updateFriendOrFamily(@PathVariable long contactNumber, @RequestBody FriendOrFamily friendOrFamilyDetails) {
        FriendOrFamily friendOrFamily = friendOrFamilyRepository.findByContactNumber(contactNumber);
        if (friendOrFamily != null) {
            if (friendOrFamilyDetails.getContactName() == null || friendOrFamilyDetails.getContactName().isEmpty()) {
                throw new InvalidFriendOrFamilyDataException("Invalid data. Contact name cannot be null or empty.");
            }
            friendOrFamily.setContactName(friendOrFamilyDetails.getContactName());
            friendOrFamily.setRelationshipType(friendOrFamilyDetails.getRelationshipType());
            FriendOrFamily updatedFriendOrFamily = friendOrFamilyRepository.save(friendOrFamily);
            return new ResponseEntity<>(updatedFriendOrFamily, HttpStatus.OK);
        } else {
            throw new FriendOrFamilyNotFoundException(contactNumber);
        }
    }

    // Delete a friend or family member
    @DeleteMapping("/{contactNumber}")
    public ResponseEntity<HttpStatus> deleteFriendOrFamily(@PathVariable long contactNumber) {
        try {
            friendOrFamilyRepository.deleteById(contactNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

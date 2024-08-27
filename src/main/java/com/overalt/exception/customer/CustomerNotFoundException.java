package com.overalt.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super("Customer not found with ID: " + id);
    }
}
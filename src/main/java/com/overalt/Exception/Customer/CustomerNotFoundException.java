package com.overalt.Exception.Customer;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(int id) {
        super("Customer not found with ID: " + id);
    }
}



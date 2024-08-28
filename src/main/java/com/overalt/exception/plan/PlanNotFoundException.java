package com.overalt.exception.plan;

public class PlanNotFoundException extends RuntimeException{
    public PlanNotFoundException(int id) {
        super("Plan not found with ID: " + id);
    }
}

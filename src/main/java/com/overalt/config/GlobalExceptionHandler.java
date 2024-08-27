package com.overalt.config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.overalt.exception.calldetails.CallDetailsNotFoundException;
import com.overalt.exception.calldetails.InvalidCallDetailsException;
import com.overalt.exception.customer.CustomerNotFoundException;
import com.overalt.exception.customer.InvalidCustomerDataException;
import com.overalt.exception.plan.InvalidPlanDataException;
import com.overalt.exception.plan.PlanNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // CustomerController
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerDataException.class)
    public ResponseEntity<String> handleInvalidCustomerDataException(InvalidCustomerDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // CallDetailsController
    @ExceptionHandler(CallDetailsNotFoundException.class)
    public ResponseEntity<String> handleCallDetailsNotFoundException(CallDetailsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCallDetailsException.class)
    public ResponseEntity<String> handleInvalidCallDetailsException(InvalidCallDetailsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // PlanController
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<String> handlePlanNotFoundException(PlanNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPlanDataException.class)
    public ResponseEntity<String> handleInvalidPlanDataException(InvalidPlanDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

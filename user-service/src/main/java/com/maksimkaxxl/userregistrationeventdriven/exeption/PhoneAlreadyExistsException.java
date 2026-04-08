package com.maksimkaxxl.userregistrationeventdriven.exeption;

public class PhoneAlreadyExistsException extends RuntimeException {

    public PhoneAlreadyExistsException(String phone) {
        super("User with phone already exists: " + phone);
    }
    
}
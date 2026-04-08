package com.maksimkaxxl.userregistrationeventdriven.exeption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email not found: " + email);
    }

}
package com.fges.rizomm.m1iii.learningagreementAPI.exception.userNotFound;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }

    public UserNotFoundException(String email) {
        super("Could not find user with email " + email);
    }

}

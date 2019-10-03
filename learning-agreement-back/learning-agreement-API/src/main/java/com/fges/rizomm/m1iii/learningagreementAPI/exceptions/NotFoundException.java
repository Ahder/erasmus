package com.fges.rizomm.m1iii.learningagreementAPI.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NotFoundException extends RuntimeException{
    public NotFoundException(Long message) {
        super("Unable to find user with id " +  message);
        log.warn("Unable to find user with id {}", () -> message);
    }
}

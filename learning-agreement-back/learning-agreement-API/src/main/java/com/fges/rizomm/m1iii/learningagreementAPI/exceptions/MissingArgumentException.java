package com.fges.rizomm.m1iii.learningagreementAPI.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException (final String message) {
        super("Unable to lauch query due to a missing argument " +  message );
        log.warn("Unable to lauch query due to {}", () -> message);
    }
}

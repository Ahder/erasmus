package com.fges.rizomm.m1iii.learningagreementAPI.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MailSendingException extends RuntimeException {

    public MailSendingException (final String message, final Throwable cause) {
        super("Unable to send mail due to : " +  message );
        log.warn("Unable to send mail due to {}", () -> message, () -> cause);
    }
}
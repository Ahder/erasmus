package com.fges.rizomm.m1iii.learningagreementAPI.exception.formNotFound;

public class FormNotFoundException extends RuntimeException {

    public FormNotFoundException(Long id) {
        super("Could not find form " + id);
    }

}

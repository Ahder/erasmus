package com.fges.rizomm.m1iii.learningagreementAPI.exception.spinneretNotFound;

public class SpinneretNotFoundException extends RuntimeException {

    public SpinneretNotFoundException(Long id) {
        super("Could not find spinneret " + id);
    }

}

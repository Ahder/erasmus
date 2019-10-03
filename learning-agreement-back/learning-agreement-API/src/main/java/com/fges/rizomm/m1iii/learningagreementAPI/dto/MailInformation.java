package com.fges.rizomm.m1iii.learningagreementAPI.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Contains any necessary information to send a mail
 */
@RequiredArgsConstructor
@Getter
public class MailInformation {

    @RequiredArgsConstructor
    @Getter
    public static class Template {
        private final String name;
        private final Map<String, Object> model;
    }

    private final Set<String> to;
    private final Template subject;
    private final Template body;
    private final Locale locale;

}


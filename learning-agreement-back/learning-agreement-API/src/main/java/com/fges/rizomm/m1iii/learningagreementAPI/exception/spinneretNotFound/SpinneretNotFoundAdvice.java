package com.fges.rizomm.m1iii.learningagreementAPI.exception.spinneretNotFound;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SpinneretNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SpinneretNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    JSONObject spinneretNotFoundHandler(SpinneretNotFoundException ex) {
        JSONObject response = new JSONObject();
        response.put("message", ex.getMessage());
        return response;
    }
}
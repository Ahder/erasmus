package com.fges.rizomm.m1iii.learningagreementAPI.exception.courseNotFound;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {
        super("Could not find course " + id);
    }

}

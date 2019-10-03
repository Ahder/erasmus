package com.fges.rizomm.m1iii.learningagreementAPI.services.course;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.course.CourseDTO;

import java.util.List;

public interface ICourseService {

    CourseDTO addCourse(CourseDTO courseDTO);
    List<CourseDTO> findAll();
    CourseDTO editCourse(CourseDTO courseDTO, Long id);
    void deleteCourse(Long id);
    CourseDTO findOne(Long id);
}

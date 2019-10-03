package com.fges.rizomm.m1iii.learningagreementAPI.controller.course;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.course.CourseDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.services.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDTO>> findAllCourses() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO findOneCourse(@PathVariable Long id) {
        return courseService.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO) {
        return this.courseService.addCourse(courseDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO editCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id) {
        return this.courseService.editCourse(courseDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCourse(@PathVariable Long id) {
        this.courseService.deleteCourse(id);
    }

}

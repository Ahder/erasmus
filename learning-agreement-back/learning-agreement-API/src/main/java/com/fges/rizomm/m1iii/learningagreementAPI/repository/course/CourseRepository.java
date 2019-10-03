package com.fges.rizomm.m1iii.learningagreementAPI.repository.course;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

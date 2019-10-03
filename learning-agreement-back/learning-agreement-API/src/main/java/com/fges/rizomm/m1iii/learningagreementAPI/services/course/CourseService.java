package com.fges.rizomm.m1iii.learningagreementAPI.services.course;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.course.CourseDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.course.Course;
import com.fges.rizomm.m1iii.learningagreementAPI.exception.courseNotFound.CourseNotFoundException;
import com.fges.rizomm.m1iii.learningagreementAPI.repository.course.CourseRepository;
import com.fges.rizomm.m1iii.learningagreementAPI.services.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService extends SuperService<Course, CourseDTO> implements ICourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDTO entityToDto(Course entity) {
        return super.getMapper().map(entity, CourseDTO.class);
    }

    public Course dtoToEntity(CourseDTO dto) {
        return super.getMapper().map(dto, Course.class);
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        return this.entityToDto(this.courseRepository.save(this.dtoToEntity(courseDTO)));
    }

    @Override
    public List<CourseDTO> findAll() {
        return this.entityListToDtoList(this.courseRepository.findAll());
    }

    @Override
    public CourseDTO findOne(Long id) {
        return this.entityToDto(this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO, Long id) {
        return this.courseRepository.findById(id).map(course -> {
            courseDTO.setId(id);
            return this.entityToDto(this.courseRepository.save(this.dtoToEntity(courseDTO)));
        }).orElseThrow(() -> new CourseNotFoundException(id));

    }

    public void deleteCourse(Long id) {
        Course courseToDelete = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        this.courseRepository.delete(courseToDelete);
    }
}

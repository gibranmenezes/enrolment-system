package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.response.CourseCreatResponse;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseCreatResponse createCourse(CourseCreateRequest request){
        if (!userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR)){
            throw new ValidationException("There is no none instructor with this id: " + request.instructorId());
        }
        var instructor = userRepository.getReferenceById(request.instructorId());
        var course = new Course(request);
        course.setInstructor(instructor);

        return new CourseCreatResponse(courseRepository.save(course));
    }
}

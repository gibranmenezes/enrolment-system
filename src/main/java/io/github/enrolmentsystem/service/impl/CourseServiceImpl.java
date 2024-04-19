package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.response.CourseCreatResponse;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.Role;
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
        if (!userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR){

        }

        var course = new Course(request);
        return new CourseCreatResponse(courseRepository.save(course));
    }
}

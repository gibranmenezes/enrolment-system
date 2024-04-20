package io.github.enrolmentsystem.domain.validations.course.creation;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseCodeValidation implements CreateCourseValidator{

    private final CourseRepository courseRepository;
    public void validate(CourseCreateRequest request) {
        if (courseRepository.existsByCode(request.code())) {
            throw new ValidationException("This course already exists!");
        }
    }
}

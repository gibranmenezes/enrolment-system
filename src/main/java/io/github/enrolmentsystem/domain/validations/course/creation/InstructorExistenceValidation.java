package io.github.enrolmentsystem.domain.validations.course.creation;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InstructorExistenceValidation implements CreateCourseValidator {

    private final UserRepository userRepository;
    public void validate(CourseCreateRequest request) {
        if (!userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR)){
            throw new ValidationException("There is no none instructor with this id: " + request.instructorId());
        }
    }

}

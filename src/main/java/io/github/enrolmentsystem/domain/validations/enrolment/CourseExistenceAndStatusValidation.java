package io.github.enrolmentsystem.domain.validations.enrolment;

import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseExistenceAndStatusValidation implements CreateEnrolmentValidator {

    private final CourseRepository courseRepository;;
    @Override
    public void validate(EnronlmentCreateRequest request) {
        var course = courseRepository.findById(request.courseId());
        if (course.isEmpty()){
            throw new ValidationException("Course not found");
        } else if (course.get().getStatus() == Status.INACTIVE) {
            throw new ValidationException("Course is inactive");
        }
    }
}

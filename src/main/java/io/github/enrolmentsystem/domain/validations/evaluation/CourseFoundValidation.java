package io.github.enrolmentsystem.domain.validations.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseFoundValidation implements EvaluationSubmitValidator {

    private final CourseRepository courseRepository;
    @Override
    public void validate(EvaluationRequest request) {
       if (courseRepository.findCourseByCode(request.courseCode()) == null) {
           throw new ValidationException("Course not found!");
       }

    }
}

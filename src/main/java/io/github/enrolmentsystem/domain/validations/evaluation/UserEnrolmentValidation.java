package io.github.enrolmentsystem.domain.validations.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.EnrolmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEnrolmentValidation implements EvaluationSubmitValidator {

    private final EnrolmentRepository enrolmentRepository;
    @Override
    public void validate(EvaluationRequest request) {
       if (enrolmentRepository.existsEnrolmentByUser(request.userId()) == null) {
           throw new ValidationException("User not enroled in this course!");
       }

    }
}

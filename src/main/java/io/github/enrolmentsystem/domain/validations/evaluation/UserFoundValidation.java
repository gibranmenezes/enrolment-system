package io.github.enrolmentsystem.domain.validations.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFoundValidation implements EvaluationSubmitValidator {

    private final UserRepository userRepository;
    @Override
    public void validate(EvaluationRequest request) {
       if (userRepository.findById(request.userId()).isEmpty()) {
           throw new ValidationException("User not found!");
       }

    }
}

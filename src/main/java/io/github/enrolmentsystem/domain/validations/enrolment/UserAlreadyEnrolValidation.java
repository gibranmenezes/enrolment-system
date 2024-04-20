package io.github.enrolmentsystem.domain.validations.enrolment;

import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.EnrolmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAlreadyEnrolValidation implements  CreateEnrolmentValidator{

    private final EnrolmentRepository repository;
    @Override
    public void validate(EnronlmentCreateRequest request) {
        if(repository.existsEnrolmentByUser(request.userId())) {
            throw new ValidationException("User already enrolled in this course");
        }

    }
}

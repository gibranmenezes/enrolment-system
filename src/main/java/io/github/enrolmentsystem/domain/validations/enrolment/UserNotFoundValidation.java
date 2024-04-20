package io.github.enrolmentsystem.domain.validations.enrolment;

import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNotFoundValidation implements CreateEnrolmentValidator{

    private final UserRepository userRepository;
    @Override
    public void validate(EnronlmentCreateRequest request) {
      var user =  userRepository.findById(request.userId());
      if (user.isEmpty()) throw new ValidationException(("User not found"));

    }
}

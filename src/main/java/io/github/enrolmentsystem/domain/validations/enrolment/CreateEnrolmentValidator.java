package io.github.enrolmentsystem.domain.validations.enrolment;

import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;

public interface CreateEnrolmentValidator {

    void validate(EnronlmentCreateRequest request);
}

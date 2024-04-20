package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.domain.enrolment.response.EnrolmentCreateResponse;

public interface EnrolmentService {

    EnrolmentCreateResponse enrol(EnronlmentCreateRequest request);
}

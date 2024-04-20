package io.github.enrolmentsystem.domain.enrolment.response;

import io.github.enrolmentsystem.domain.enrolment.Enrolment;

public record EnrolmentCreateResponse(Long id, String userName, String courseCode) {
    public EnrolmentCreateResponse(Enrolment enrolment) {
        this(enrolment.getId(), enrolment.getUser().getUsername(),enrolment.getCourse().getCode());
    }
}

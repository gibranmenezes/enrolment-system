package io.github.enrolmentsystem.domain.validations.course.creation;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;

public interface CreateCourseValidator {
    void validate(CourseCreateRequest request);
}

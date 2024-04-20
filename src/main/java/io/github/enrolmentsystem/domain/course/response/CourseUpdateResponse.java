package io.github.enrolmentsystem.domain.course.response;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;

import java.time.LocalDate;

public record CourseUpdateResponse(String name, String code, Status status, LocalDate inactivatedAr) {
    public CourseUpdateResponse(Course course) {
        this(course.getName(), course.getCode(), course.getStatus(), course.getInactivatedAt());
    }
}

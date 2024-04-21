package io.github.enrolmentsystem.domain.course.response;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;

public record CourseCreateResponse(Long id, String name, String code, Status status, String instructorName) {
    public CourseCreateResponse(Course course) {
        this(course.getId(), course.getName(), course.getCode(), course.getStatus(), course.getInstructor().getName());
    }
}

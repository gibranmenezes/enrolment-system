package io.github.enrolmentsystem.domain.course.response;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;

public record CourseCreatResponse(String name, String code, Status status, String instructorName) {
    public CourseCreatResponse(Course course) {
        this(course.getName(), course.getCode(), course.getStatus(), course.getInstructor().getName());
    }
}

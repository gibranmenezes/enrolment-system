package io.github.enrolmentsystem.domain.course.response;

import io.github.enrolmentsystem.domain.course.Course;

public record CourseCreatResponse(String name, String code, Long instructorId) {
    public CourseCreatResponse(Course course) {
        this(course.getName(), course.getCode(), course.getInstructor().getId());
    }
}

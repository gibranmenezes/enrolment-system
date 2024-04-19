package io.github.enrolmentsystem.domain.course.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]+(?:-[a-zA-Z]+){0,9}$")
        String code,
        String description,
        Long instructorId) {
}

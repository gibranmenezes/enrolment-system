package io.github.enrolmentsystem.domain.enrolment.request;

import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Native;

public record EnronlmentCreateRequest(@NotNull Long userId, @NotNull Long courseId) {
}

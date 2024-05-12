package io.github.enrolmentsystem.domain.enrolment.request;

import jakarta.validation.constraints.NotNull;

public record EnronlmentCreateRequest(@NotNull Long userId, @NotNull Long courseId) {
}

package io.github.enrolmentsystem.domain.authentication;

import jakarta.validation.constraints.NotNull;

public record AuthenticationData(@NotNull String username, @NotNull String password) {
}

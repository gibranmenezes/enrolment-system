package io.github.enrolmentsystem.domain.user.response;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;

import java.time.LocalDate;

public record UserCreateResponse(String name, String userName, String email, Role role, LocalDate createdAt) {
    public UserCreateResponse(User user) {
        this(user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}

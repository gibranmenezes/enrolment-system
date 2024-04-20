package io.github.enrolmentsystem.domain.user.response;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;

public record UserCreateResponse(String name, String email, Role role) {
    public UserCreateResponse(User user) {
        this(user.getName(), user.getEmail(), user.getRole());
    }
}

package io.github.enrolmentsystem.domain.user.request;

import io.github.enrolmentsystem.domain.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequest(String name, @Pattern(regexp = "^[a-z]{1,20}$") String username
        , @Email String email, String password, Role role) {
}

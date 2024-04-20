package io.github.enrolmentsystem.domain.user.request;

import io.github.enrolmentsystem.domain.user.Role;
import jakarta.validation.constraints.*;

public record UserCreateRequest(@NotBlank@NotNull String name,
                                @Pattern(regexp = "^[a-z]{1,20}$")String username
        , @Email String email, @NotBlank@NotNull String password, @NotNull Role role) {
}

package io.github.enrolmentsystem.domain.user.response;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;

import java.time.LocalDate;

public record UserDetailsResponse(String name, String email) {
    public UserDetailsResponse(User user){
        this(user.getName(), user.getEmail());
    }

}

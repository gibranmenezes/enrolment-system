package io.github.enrolmentsystem.domain.user;

import lombok.Getter;

public enum Role {

    STUDENT("student"),
    INSTRUCTOR("instructor"),
    ADMIN("admin");

    @Getter
    private  final String value;
    Role(String value) {
        this.value = value;
    }
}

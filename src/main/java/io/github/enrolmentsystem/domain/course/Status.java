package io.github.enrolmentsystem.domain.course;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}

package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.user.request.UserCreateRequest;
import io.github.enrolmentsystem.domain.user.response.UserCreateResponse;

public interface UserService {

    UserCreateResponse register(UserCreateRequest request);
}

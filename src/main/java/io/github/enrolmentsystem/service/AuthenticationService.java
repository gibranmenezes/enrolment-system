package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.authentication.AuthenticationData;
import io.github.enrolmentsystem.infra.security.TokenDataJwt;

public interface AuthenticationService {

    TokenDataJwt authenticate(AuthenticationData data);
}

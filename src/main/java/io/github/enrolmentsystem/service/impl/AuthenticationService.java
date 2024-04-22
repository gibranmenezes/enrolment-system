package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.authentication.AuthenticationData;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.infra.security.TokenDataJwt;
import io.github.enrolmentsystem.infra.security.TokenService;
import io.github.enrolmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public TokenDataJwt authenticate(AuthenticationData data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return new TokenDataJwt(tokenJWT);
    }
}

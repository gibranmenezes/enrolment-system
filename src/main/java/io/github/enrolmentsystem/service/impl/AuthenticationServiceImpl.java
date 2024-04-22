package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.authentication.AuthenticationData;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.infra.security.TokenDataJwt;
import io.github.enrolmentsystem.infra.security.TokenService;
import io.github.enrolmentsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public TokenDataJwt authenticate(AuthenticationData data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return new TokenDataJwt(tokenJWT);
    }
}

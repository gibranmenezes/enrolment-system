package io.github.enrolmentsystem.controller.authentication;

import io.github.enrolmentsystem.domain.authentication.AuthenticationData;
import io.github.enrolmentsystem.infra.security.TokenDataJwt;
import io.github.enrolmentsystem.service.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<TokenDataJwt> login(@RequestBody @Valid AuthenticationData data){
        var token = authenticationService.authenticate(data);
        return ResponseEntity.ok(token);

    }
}

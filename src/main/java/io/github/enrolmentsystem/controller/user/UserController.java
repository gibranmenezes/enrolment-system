package io.github.enrolmentsystem.controller.user;

import io.github.enrolmentsystem.domain.user.request.UserCreateRequest;
import io.github.enrolmentsystem.domain.user.response.UserCreateResponse;
import io.github.enrolmentsystem.domain.user.response.UserDetailsResponse;
import io.github.enrolmentsystem.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> register(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable String username){
        return ResponseEntity.ok(userService.getByUsername(username));
    }
}

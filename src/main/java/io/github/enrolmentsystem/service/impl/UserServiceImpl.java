package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.domain.user.request.UserCreateRequest;
import io.github.enrolmentsystem.domain.user.response.UserCreateResponse;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;

     public UserCreateResponse register(UserCreateRequest request) {
          if (!userRepository.existsByUsernameAndEmail(request.username(), request.email())) {
               throw new ValidationException("User name and email already registered");
          }
          var user = new User(request);

          return new UserCreateResponse(user);
     }
}

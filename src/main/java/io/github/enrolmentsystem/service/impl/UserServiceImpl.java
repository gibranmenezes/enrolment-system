package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.domain.user.request.UserCreateRequest;
import io.github.enrolmentsystem.domain.user.response.UserCreateResponse;
import io.github.enrolmentsystem.domain.user.response.UserDetailsResponse;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;

     @Override
     @Transactional
     public UserCreateResponse register(UserCreateRequest request) {
          if (userRepository.existsByUsernameAndEmail(request.username(), request.email())) {
               throw new ValidationException("Username and email already registered");
          }
          var encriptedPassword = new BCryptPasswordEncoder().encode(request.password());
          var user = new User(request.username(), request.email(), encriptedPassword);
          user.setName(request.name());
          user.setRole(request.role());
          user.setCreatedAt(LocalDate.now());

          return new UserCreateResponse(userRepository.save(user));
     }
     public UserDetailsResponse getByUsername(String username) {
          var user = userRepository.getUserByUsername(username);
          return new UserDetailsResponse(user);
     }
}

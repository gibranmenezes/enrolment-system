package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
     private final UserRepository userRepository;




}

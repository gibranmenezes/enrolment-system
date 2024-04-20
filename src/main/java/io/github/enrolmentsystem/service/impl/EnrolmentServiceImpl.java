package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.enrolment.Enrolment;
import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.domain.enrolment.response.EnrolmentCreateResponse;
import io.github.enrolmentsystem.domain.validations.enrolment.CreateEnrolmentValidator;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.EnrolmentRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.EnrolmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrolmentServiceImpl implements EnrolmentService {

    private final EnrolmentRepository enrolmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    private  List<CreateEnrolmentValidator> createValidators = new ArrayList<>();

    @Override
    @Transactional
    public EnrolmentCreateResponse enrol(EnronlmentCreateRequest request){
        createValidators.forEach(v -> v.validate(request));

        var course = courseRepository.getReferenceById(request.userId());
        var user = userRepository.getReferenceById(request.courseId());
        var enrolment = new Enrolment(user, course);

        return new EnrolmentCreateResponse(enrolmentRepository.save(enrolment));
    }


}

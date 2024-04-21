package io.github.enrolmentsystem.controller.enrolment;

import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.domain.enrolment.response.EnrolmentCreateResponse;
import io.github.enrolmentsystem.service.impl.EnrolmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enrolment")
@RequiredArgsConstructor
public class EnrolmentController {

    private final EnrolmentServiceImpl enrolmentService;

    @PostMapping
    public ResponseEntity<EnrolmentCreateResponse> enrol(@RequestBody EnronlmentCreateRequest request){
        return ResponseEntity.ok(enrolmentService.enrol(request));
    }
}

package io.github.enrolmentsystem.controller.course;

import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseCreateResponse;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import io.github.enrolmentsystem.domain.course.response.CourseUpdateResponse;
import io.github.enrolmentsystem.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseService;

    @PostMapping
    public ResponseEntity<CourseCreateResponse> create(@Valid @RequestBody CourseCreateRequest request){
        return ResponseEntity.ok(courseService.createCourse(request));
    }
    @GetMapping
    public ResponseEntity<Page<CourseDetailsResponse>> getCoursesByStatus(Status status,
                                                          @PageableDefault(size = 10) Pageable pagination){
        return ResponseEntity.ok(courseService.getCoursesByStatus(status, pagination));
    }
    @PutMapping()
    public ResponseEntity<CourseUpdateResponse> inactivateCourse(@RequestBody String code){
        return ResponseEntity.ok(courseService.inactivateCourse(code));
    }


}

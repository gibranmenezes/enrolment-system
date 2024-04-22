package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseCreateResponse;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import io.github.enrolmentsystem.domain.course.response.CourseUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseCreateResponse createCourse(CourseCreateRequest request);

    CourseUpdateResponse inactivateCourse(String code);

    Page<CourseDetailsResponse> getCoursesByStatus(Status status, Pageable pagination);

    interface EnrolmentService {
    }
}

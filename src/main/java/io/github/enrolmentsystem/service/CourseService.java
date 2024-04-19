package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseCreatResponse;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseCreatResponse createCourse(CourseCreateRequest request);

    void inactivateCourse(Long id);

    Page<CourseDetailsResponse> getCoursesByStatus(Status status, Pageable pagination);

}

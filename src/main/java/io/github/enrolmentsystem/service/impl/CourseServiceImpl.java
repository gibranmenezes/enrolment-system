package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseCreatResponse;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import io.github.enrolmentsystem.domain.course.response.CourseUpdateResponse;
import io.github.enrolmentsystem.domain.validations.course.creation.CreateCourseValidator;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public  List<CreateCourseValidator> createValidators = new ArrayList<>();



    @Override
    @Transactional
    public CourseCreatResponse createCourse(CourseCreateRequest request){
        createValidators.forEach(v -> v.validate(request));

        var instructor = userRepository.getReferenceById(request.instructorId());
        var course = new Course(request);
        course.setInstructor(instructor);
        return new CourseCreatResponse(courseRepository.save(course));
    }

    @Override
    public Page<CourseDetailsResponse> getCoursesByStatus(Status status, Pageable pagination){
        Page<Course> coursePage = courseRepository.findAllByStatus(status, pagination);

        return coursePage.map(course -> {
            HashMap<String, String> courseDetails = new HashMap<>();
            courseDetails.put("name", course.getName());
            courseDetails.put("code", course.getCode());
            courseDetails.put("description", course.getDescription());
            courseDetails.put("instructorUserName", course.getInstructor().getUsername());
            courseDetails.put("createdAt", course.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            if (status == Status.INACTIVE){
                courseDetails.put("inactivatedAt", course.getInactivatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

          return new CourseDetailsResponse(courseDetails);
        });

    }
    @Override
    public CourseUpdateResponse inactivateCourse(String code){
       var course = courseRepository.findCourseByCode(code);
      if (course == null) { throw new ValidationException("Course not found"); }
       course.inactivateCourse();

       return new CourseUpdateResponse(courseRepository.save(course));
    }

}

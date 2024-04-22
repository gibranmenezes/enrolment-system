package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;

public interface NotificationService {

    void lowRatingNotify(Course course, String evaluationDescription);
}

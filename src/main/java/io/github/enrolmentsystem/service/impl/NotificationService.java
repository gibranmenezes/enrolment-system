package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.utils.EmailSenderUtil;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

public static final String LOW_RATE_SUBJECT = "Course low rating evaluated";

    public void lowRatingNotify(Course course, String evaluationDescription){
        var email = course.getInstructor().getEmail();
        EmailSenderUtil.send(email, evaluationDescription, LOW_RATE_SUBJECT);

    }
}

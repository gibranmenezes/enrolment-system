package io.github.enrolmentsystem.domain.enrolment;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    private LocalDate enrolmentAt;

    public Enrolment(User student, Course course){
        this.student = student;
        this.course = course;
        this.enrolmentAt = LocalDate.now();
    }
}

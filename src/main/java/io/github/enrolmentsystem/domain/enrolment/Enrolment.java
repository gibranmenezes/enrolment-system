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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Course course;
    private LocalDate enrollmentAt;

    public Enrolment(User user, Course course){
        this.user = user;
        this.course = course;
        this.enrollmentAt = LocalDate.now();
    }
}

package io.github.enrolmentsystem.domain.course;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
public class Course  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private String code;

    @Setter
    private String description;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User instructor;

    @Setter
    private Status status;

    @Setter
    private LocalDate createdAt;

    @Setter
    private LocalDate inactivedAt;

    public Course(CourseCreateRequest request) {
        this.status = Status.ACTIVE;
        this.name = request.name();
        this.code = request.code();
        this.description = request.description();
        this.createdAt = LocalDate.now();
        this.inactivedAt = null;

    }

    public void inactivateCourse(){
        this.status = Status.INACTIVE;
    }


}

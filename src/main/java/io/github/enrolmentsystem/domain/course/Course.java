package io.github.enrolmentsystem.domain.course;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Status status;

    @Setter
    private LocalDate createdAt;

    @Setter
    private LocalDate inactivatedAt;

    @OneToMany(mappedBy = "course")
    private List<CourseEvaluation> courseEvaluations = new ArrayList<>();

    public Course(CourseCreateRequest request) {
        this.status = Status.ACTIVE;
        this.name = request.name();
        this.code = request.code();
        this.description = request.description();
        this.createdAt = LocalDate.now();
        this.inactivatedAt = null;

    }
    public void inactivateCourse(){
        this.status = Status.INACTIVE;
        this.inactivatedAt = LocalDate.now();
    }

    public void addEvaluation(CourseEvaluation evaluation){
        this.courseEvaluations.add(evaluation);
    }


}

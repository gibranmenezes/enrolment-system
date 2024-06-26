package io.github.enrolmentsystem.domain.course;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.enrolment.Enrolment;
import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
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
    @ManyToOne(fetch = FetchType.LAZY)
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
    private List<Enrolment> enrolments = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseEvaluation> evaluations = new ArrayList<>();

    public Course(CourseCreateRequest request) {
        this.status = Status.ACTIVE;
        this.name = request.name();
        this.code = request.code();
        this.description = request.description();
        this.createdAt = LocalDate.now();
        this.inactivatedAt = null;

    }

    
    public Course(String name, String code, String description, User instructor) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.instructor = instructor;
        this.status = Status.ACTIVE;
        this.createdAt = LocalDate.now();
        this.inactivatedAt = null;
    }


    public void inactivateCourse(){
        this.status = Status.INACTIVE;
        this.inactivatedAt = LocalDate.now();
    }

    public void addEvaluation(CourseEvaluation evaluation){
        this.evaluations.add(evaluation);
    }

    public void addEnrolment(Enrolment enrolment){
        this.enrolments.add(enrolment);
    }


}

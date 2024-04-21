package io.github.enrolmentsystem.domain.evaluation;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity()
@Table(name = "course_evaluation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int rating;
    private String evaluationDescription;
    LocalDate submitedAt;

    public CourseEvaluation(EvaluationRequest data){
        this.rating = data.rating();
        this.evaluationDescription = data.evaluationDescription();
        this.submitedAt = LocalDate.now();
    }


}

package io.github.enrolmentsystem.domain.course;

import io.github.enrolmentsystem.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String code;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User instructor;

    private Boolean Status;

    private LocalDate createdAt;

    private LocalDate inactivedAt;


}

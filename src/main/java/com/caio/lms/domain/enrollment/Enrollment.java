package com.caio.lms.domain.enrollment;


import com.caio.lms.domain.course.Course;
import com.caio.lms.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"})
)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledAt;

    private boolean completed;

    @OneToMany(
            mappedBy = "enrollment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<com.caio.lms.domain.progress.LessonProgress> lessonProgresses;
}

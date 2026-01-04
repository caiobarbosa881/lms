package com.caio.lms.domain.course;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contentUrl;

    @Enumerated(EnumType.STRING)
    private LessonType type;

    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;
}


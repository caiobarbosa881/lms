package com.caio.lms.domain.course;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // índice de ordenação do módulo dentro do curso
    @Column(nullable = false)
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("orderIndex ASC")
    private List<Lesson> lessons;
}

package com.caio.lms.service;

// Aqui são os Domains core
import com.caio.lms.domain.course.Course;
import com.caio.lms.domain.enrollment.Enrollment;
import com.caio.lms.domain.user.User;

// Aqui são os Repositories
import com.caio.lms.repository.CourseRepository;
import com.caio.lms.repository.EnrollmentRepository;
import com.caio.lms.repository.UserRepository;

// Annotation Spring para ajudar na criação do Service
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Realiza a matrícula de um aluno em um curso.
     *
     * Regras:
     * - O aluno deve existir
     * - O curso deve existir
     * - Não permite matrícula duplicada
     */
    public Enrollment enrollStudentInCourse(Long studentId, Long courseId) {

        // Verifica matrícula duplicada
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Aluno já está matriculado neste curso");
        }

        // Busca aluno
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        // Busca curso
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Cria matrícula
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        return enrollmentRepository.save(enrollment);
    }

    /**
     * Verifica se um aluno está matriculado em um curso.
     */
    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }

    /**
     * Busca uma matrícula pelo aluno e curso.
     */
    public Enrollment getEnrollment(Long studentId, Long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
    }
}

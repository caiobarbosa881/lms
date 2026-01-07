package com.caio.lms.service;

import com.caio.lms.domain.course.Course;
import com.caio.lms.domain.course.Module;
import com.caio.lms.domain.course.Lesson;
import com.caio.lms.domain.user.User;
import com.caio.lms.repository.CourseRepository;
import com.caio.lms.repository.ModuleRepository;
import com.caio.lms.repository.LessonRepository;
import com.caio.lms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public CourseService(
            CourseRepository courseRepository,
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository,
            UserRepository userRepository
    ) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(String title, String description, Long instructorId) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    /**
     * Adiciona um módulo a um curso existente.
     */
    public Module addModuleToCourse(Long courseId, String moduleTitle, Integer order) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Module module = new Module();
        module.setTitle(moduleTitle);
        module.setOrder(order);
        module.setCourse(course);

        return moduleRepository.save(module);
    }

    /**
     * Adiciona uma aula a um módulo.
     */
    public Lesson addLessonToModule(
            Long moduleId,
            String title,
            String videoUrl,
            Integer order
    ) {

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Módulo não encontrado"));

        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setVideoUrl(videoUrl);
        lesson.setOrder(order);
        lesson.setModule(module);

        return lessonRepository.save(lesson);
    }

    /**
     * Retorna todos os cursos de um instrutor.
     */
    public List<Course> getCoursesByInstructor(Long instructorId) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        return courseRepository.findByInstructor(instructor);
    }

}

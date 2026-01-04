package com.caio.lms.repository;

import com.caio.lms.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long> {
}

package com.caio.lms.repository;

import com.caio.lms.domain.course.Module;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
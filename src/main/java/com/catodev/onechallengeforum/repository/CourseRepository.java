package com.catodev.onechallengeforum.repository;

import com.catodev.onechallengeforum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

package com.catodev.onechallengeforum.repository;

import com.catodev.onechallengeforum.model.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndCourseId(String title, Long courseId);

    Page<Topic> findByStatus(String status, Pageable pageable);
}

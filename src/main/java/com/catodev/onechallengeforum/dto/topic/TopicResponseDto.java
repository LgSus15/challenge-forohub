package com.catodev.onechallengeforum.dto.topic;

import java.time.LocalDateTime;

public record TopicResponseDto(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        Long authorId,
        String authorName,
        Long courseId,
        String courseName
) {}
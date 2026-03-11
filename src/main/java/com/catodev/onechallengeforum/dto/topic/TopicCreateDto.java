package com.catodev.onechallengeforum.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateDto(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Message is required")
        String message,

        @NotNull(message = "Author ID is required")
        Long authorId,

        @NotNull(message = "Course ID is required")
        Long courseId
) {}
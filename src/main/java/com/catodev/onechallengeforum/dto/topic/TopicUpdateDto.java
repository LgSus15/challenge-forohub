package com.catodev.onechallengeforum.dto.topic;

import jakarta.validation.constraints.Size;

public record TopicUpdateDto(
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String title,
        @Size(min = 1, message = "Message must be not be empty")
        String message
) {}

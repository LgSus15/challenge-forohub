package com.catodev.onechallengeforum.mapper;

import com.catodev.onechallengeforum.dto.topic.TopicCreateDto;
import com.catodev.onechallengeforum.dto.topic.TopicResponseDto;
import com.catodev.onechallengeforum.model.Course;
import com.catodev.onechallengeforum.model.Topic;
import com.catodev.onechallengeforum.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    // Entity → Response DTO (para GET)
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseName", source = "course.name")
    TopicResponseDto toDTO(Topic topic);

    // Create DTO + relaciones → Entity (para POST)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "message", source = "dto.message")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "course", source = "course")
    Topic toEntity(TopicCreateDto dto, User author, Course course);
}
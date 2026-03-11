package com.catodev.onechallengeforum.service.topic;

import com.catodev.onechallengeforum.dto.topic.TopicCreateDto;
import com.catodev.onechallengeforum.dto.topic.TopicResponseDto;
import com.catodev.onechallengeforum.dto.topic.TopicUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {
    TopicResponseDto create(TopicCreateDto dto);
    Page<TopicResponseDto> findAll(Pageable pageable);
    TopicResponseDto findById(Long id);
    TopicResponseDto update(Long id, TopicUpdateDto dto);
    void delete(Long id);

}

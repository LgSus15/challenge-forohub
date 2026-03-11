package com.catodev.onechallengeforum.service.topic;

import com.catodev.onechallengeforum.dto.topic.TopicCreateDto;
import com.catodev.onechallengeforum.dto.topic.TopicResponseDto;
import com.catodev.onechallengeforum.dto.topic.TopicUpdateDto;
import com.catodev.onechallengeforum.exception.DuplicateResourceException;
import com.catodev.onechallengeforum.exception.ResourceNotFoundException;
import com.catodev.onechallengeforum.mapper.TopicMapper;
import com.catodev.onechallengeforum.model.Course;
import com.catodev.onechallengeforum.model.Topic;
import com.catodev.onechallengeforum.model.User;
import com.catodev.onechallengeforum.repository.CourseRepository;
import com.catodev.onechallengeforum.repository.TopicRepository;
import com.catodev.onechallengeforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TopicMapper topicMapper;

    @Override
    @Transactional
    public TopicResponseDto create(TopicCreateDto dto) {
        if (topicRepository.existsByTitleAndCourseId(dto.title(), dto.courseId())) {
            throw new DuplicateResourceException("Topic with this title already exists in this course");
        }
        User author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.authorId()));
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + dto.courseId()));
        Topic topic = topicMapper.toEntity(dto, author, course);
        topic = topicRepository.save(topic);
        return topicMapper.toDTO(topic);
    }

    @Override
    public Page<TopicResponseDto> findAll(Pageable pageable) {
        return topicRepository.findByStatus("ACTIVE", pageable)
                .map(topicMapper::toDTO);
    }

    @Override
    public TopicResponseDto findById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + id));
        return topicMapper.toDTO(topic);
    }

    @Override
    @Transactional
    public TopicResponseDto update(Long id, TopicUpdateDto dto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + id));
        if (dto.title() != null) {
            topic.setTitle(dto.title());
        }
        if (dto.message() != null) {
            topic.setMessage(dto.message());
        }
        return topicMapper.toDTO(topic);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + id));
        topic.setStatus("DELETED");
    }
}

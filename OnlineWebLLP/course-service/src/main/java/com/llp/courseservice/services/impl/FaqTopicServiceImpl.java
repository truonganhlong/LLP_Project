package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.FaqTopic.FaqTopicRequest;
import com.llp.courseservice.dtos.FaqTopic.FaqTopicResponse;
import com.llp.courseservice.entities.FaqTopic;
import com.llp.courseservice.mappers.FaqTopicMapper;
import com.llp.courseservice.repositories.FaqTopicRepository;
import com.llp.courseservice.repositories.TopicRepository;
import com.llp.courseservice.services.FaqTopicService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqTopicServiceImpl implements FaqTopicService {
    private final FaqTopicRepository faqTopicRepository;
    private final TopicRepository topicRepository;
    @Override
    public List<FaqTopicResponse> getAllByTopic(int topicId) {
        try {
            List<FaqTopic> faqTopics = faqTopicRepository.getByTopic(topicId);
            return faqTopics.stream().map(FaqTopicMapper::convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(FaqTopicRequest request) {
        try {
            FaqTopic faqTopic = FaqTopic.builder()
                    .question(request.getQuestion())
                    .answer(request.getAnswer())
                    .topic(topicRepository.getById(request.getTopicId()))
                    .build();
            faqTopicRepository.save(faqTopic);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            FaqTopic faqTopic = faqTopicRepository.getById(id);
            if(Objects.isNull(faqTopic)){
                throw new NotFoundException("Not found in database");
            }
            faqTopicRepository.delete(faqTopic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

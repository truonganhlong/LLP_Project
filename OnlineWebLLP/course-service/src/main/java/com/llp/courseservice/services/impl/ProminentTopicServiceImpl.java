package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicAdminResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicCreateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUpdateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUserResponse;
import com.llp.courseservice.entities.Language;
import com.llp.courseservice.entities.ProminentTopic;
import com.llp.courseservice.entities.Tag;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.mappers.LanguageMapper;
import com.llp.courseservice.mappers.ProminentTopicMapper;
import com.llp.courseservice.mappers.TagMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.ProminentTopicRepository;
import com.llp.courseservice.services.ProminentTopicService;
import com.llp.courseservice.services.TopicService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProminentTopicServiceImpl implements ProminentTopicService {
    private final ProminentTopicRepository prominentTopicRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<ProminentTopicAdminResponse> getAllByAdmin() {
        try {
            List<ProminentTopic> prominentTopics = prominentTopicRepository.findAll();
            return prominentTopics.stream().map(ProminentTopicMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<ProminentTopicUserResponse> getAllByUser() {
        try {
            List<ProminentTopic> prominentTopics = prominentTopicRepository.findAll();
            List<ProminentTopicUserResponse> data = prominentTopics.stream().map(ProminentTopicMapper::convertToUserResponse).collect(Collectors.toList());
            for (var prominentTopic:data) {
                List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourseByTopicId(prominentTopic.getTopicId().intValue());
                List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
                for (var courseCardResponse:courseCardResponses) {
                    //courseCardResponse.setInstructor();
                    //courseCardResponse.setDiscountPrice();
                }
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public ProminentTopicAdminResponse getById(int id) {
        try {
            ProminentTopic prominentTopic = prominentTopicRepository.getById(id);
            if(Objects.isNull(prominentTopic)){
                throw new NotFoundException("Not found in database");
            }
            return ProminentTopicMapper.convertToAdminResponse(prominentTopic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(ProminentTopicCreateRequest request) {

    }

    @Override
    public void update(int id, ProminentTopicUpdateRequest request) {

    }

    @Override
    public void delete(int id) {

    }
}

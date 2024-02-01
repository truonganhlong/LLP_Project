package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicAdminResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicCreateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUpdateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUserResponse;
import com.llp.courseservice.entities.ProminentTopic;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.mappers.ProminentTopicMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.ProminentTopicRepository;
import com.llp.courseservice.repositories.TopicRepository;
import com.llp.courseservice.services.DiscountService;
import com.llp.courseservice.services.ProminentTopicService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProminentTopicServiceImpl implements ProminentTopicService {
    private final ProminentTopicRepository prominentTopicRepository;
    private final CourseRepository courseRepository;
    private final DiscountService discountService;
    private final TopicRepository topicRepository;

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
    public List<ProminentTopicUserResponse> getAllByUser(Integer pageNo, Integer pageSize) {
        try {
            List<ProminentTopic> prominentTopics = prominentTopicRepository.findAll();
            List<ProminentTopicUserResponse> data = prominentTopics.stream().map(ProminentTopicMapper::convertToUserResponse).collect(Collectors.toList());
            Pageable paging = PageRequest.of(pageNo, pageSize);
            for (var prominentTopic:data) {
                List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourseByTopicId(prominentTopic.getTopicId().intValue(), paging);
                List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
                for (var courseCardResponse:courseCardResponses) {
                    //feign client user later
                    courseCardResponse.setInstructor(null);
                    courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId()));
                }
                prominentTopic.setCourseCardResponses(courseCardResponses);
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
        try {
            ProminentTopic prominentTopic = ProminentTopic.builder()
                    .title(request.getTitle())
                    .overview(request.getOverview())
                    .topic(topicRepository.getById(request.getTopicId()))
                    .build();
            prominentTopicRepository.save(prominentTopic);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, ProminentTopicUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            ProminentTopic prominentTopic = prominentTopicRepository.getById(id);
            if(Objects.isNull(prominentTopic)){
                throw new NotFoundException("Not found in database");
            }
            prominentTopic.setTitle(request.getTitle() != null ? request.getTitle() : prominentTopic.getTitle());
            prominentTopic.setOverview(request.getOverview() != null ? request.getOverview() : prominentTopic.getOverview());
            prominentTopicRepository.save(prominentTopic);
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            ProminentTopic prominentTopic = prominentTopicRepository.getById(id);
            if(Objects.isNull(prominentTopic)){
                throw new NotFoundException("Not found in database");
            }
            prominentTopicRepository.delete(prominentTopic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

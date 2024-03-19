package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Topic.TopicAdminResponse;
import com.llp.courseservice.dtos.Topic.TopicCreateRequest;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import com.llp.courseservice.dtos.Topic.TopicUpdateRequest;
import com.llp.courseservice.entities.Topic;
import com.llp.courseservice.mappers.TopicMapper;
import com.llp.courseservice.repositories.SubCategoryRepository;
import com.llp.courseservice.repositories.TopicRepository;
import com.llp.courseservice.services.TopicService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "topic")
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final SubCategoryRepository subCategoryRepository;
    @Override
    @Cacheable(cacheNames = "topic/getAll")
    public List<TopicAdminResponse> getAll() {
        try {
            List<Topic> topics = topicRepository.findAll();
            return topics.stream().map(TopicMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "topic/getByAdminFilterBySubCategory", key = "#subCategoryId")
    public List<TopicAdminResponse> getByAdminFilterBySubCategory(int subCategoryId) {
        try {
            List<Topic> topics = topicRepository.getByAdminFilterBySubCategory(subCategoryId);
            return topics.stream().map(TopicMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "topic/getByUserFilterBySubCategory", key = "#subCategoryId")
    public List<TopicNameResponse> getByUserFilterBySubCategory(int subCategoryId) {
        try {
            List<TopicRepository.TopicByName> topics = topicRepository.getByUserFilterBySubCategory(subCategoryId);
            return topics.stream().map(TopicMapper::convertToUserResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "topic/getById", key = "#id")
    public TopicAdminResponse getById(int id) {
        try {
            Topic topic = topicRepository.getById(id);
            if(Objects.isNull(topic)){
                throw new NotFoundException("Not found in database");
            }
            return TopicMapper.convertToAdminResponse(topic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "topic/getAll"),
                    @CacheEvict(cacheNames = "topic/getByAdminFilterBySubCategory", key = "#request.subCategoryId"),
                    @CacheEvict(cacheNames = "topic/getByUserFilterBySubCategory", key = "#request.subCategoryId")
            }
    )
    public void create(TopicCreateRequest request) {
        try {
            Topic topic = Topic.builder()
                    .name(request.getName())
                    .allAbout(request.getAllAbout())
                    .description(request.getDescription())
                    .status(true)
                    .subCategory(subCategoryRepository.getById(request.getSubCategoryId()))
                    .build();
            topicRepository.save(topic);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "topic/getAll"),
                    @CacheEvict(cacheNames = "topic/getByAdminFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getByUserFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getById", key = "#id")
            }
    )
    public void update(int id, TopicUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Topic topic = topicRepository.getById(id);
            if(Objects.isNull(topic)){
                throw new NotFoundException("Not found in database");
            }
            topic.setName(request.getName() != null ? request.getName() : topic.getName());
            topic.setAllAbout(request.getAllAbout() != null ? request.getAllAbout() : topic.getAllAbout());
            topic.setDescription(request.getDescription() != null ? request.getDescription() : topic.getDescription());
            topicRepository.save(topic);
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "topic/getAll"),
                    @CacheEvict(cacheNames = "topic/getByAdminFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getByUserFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getById", key = "#id")
            }
    )
    public void updateStatus(int id) {
        try {
            Topic topic = topicRepository.getById(id);
            if(Objects.isNull(topic)){
                throw new NotFoundException("Not found in database");
            }
            topic.setStatus(!topic.isStatus());
            topicRepository.save(topic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "topic/getAll"),
                    @CacheEvict(cacheNames = "topic/getByAdminFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getByUserFilterBySubCategory", allEntries = true),
                    @CacheEvict(cacheNames = "topic/getById", key = "#id")
            }
    )
    public void delete(int id) {
        try {
            Topic topic = topicRepository.getById(id);
            if(Objects.isNull(topic)){
                throw new NotFoundException("Not found in database");
            }
            topicRepository.delete(topic);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

}

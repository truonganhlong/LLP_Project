package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Tag.TagCreateRequest;
import com.llp.courseservice.dtos.Tag.TagResponse;
import com.llp.courseservice.dtos.Tag.TagUpdateRequest;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.entities.CourseTag;
import com.llp.courseservice.entities.Tag;
import com.llp.courseservice.mappers.TagMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.CourseTagRepository;
import com.llp.courseservice.repositories.TagRepository;
import com.llp.courseservice.services.TagService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final CourseTagRepository courseTagRepository;
    private final CourseRepository courseRepository;
    @Override
    public List<TagResponse> getAll() {
        try {
            List<Tag> tags = tagRepository.findAll();
            return tags.stream().map(TagMapper::convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public TagResponse getById(int id) {
        try {
            Tag tag = tagRepository.getById(id);
            if(Objects.isNull(tag)){
                throw new NotFoundException("Not found in database");
            }
            return TagMapper.convertToResponse(tag);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(TagCreateRequest request) {
        try {
            Tag tag = Tag.builder()
                    .name(request.getName())
                    .build();
            tagRepository.save(tag);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, TagUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Tag tag = tagRepository.getById(id);
            if(Objects.isNull(tag)){
                throw new NotFoundException("Not found in database");
            }
            tag.setName(request.getName() != null ? request.getName() : tag.getName());
            tagRepository.save(tag);
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
            Tag tag = tagRepository.getById(id);
            if(Objects.isNull(tag)){
                throw new NotFoundException("Not found in database");
            }
            tagRepository.delete(tag);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateBestsellerTag() {
        try {
            courseTagRepository.deleteBestsellerTag();
            List<Course> courses = courseRepository.getAllOrderBySaleNum(PageRequest.of(0, 25));
            for (var course:courses) {
                courseTagRepository.updateBestsellerTag(String.valueOf(course.getId()), LocalDate.now());
            }
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateHighestRatedTag() {
        try {
            courseTagRepository.deleteHighestRatedTag();
            List<Course> courses = courseRepository.getAllOrderByRating(PageRequest.of(0, 25));
            for (var course:courses) {
                courseTagRepository.updateHighestRatedTag(String.valueOf(course.getId()), LocalDate.now());
            }
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "UTC") // Run daily at midnight
    public void deleteExpiredNewTags() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        List<CourseTag> expiredTags = courseTagRepository.findByCreatedAtBefore(sevenDaysAgo);
        // Delete the expired tags
        courseTagRepository.deleteAll(expiredTags);
    }

}

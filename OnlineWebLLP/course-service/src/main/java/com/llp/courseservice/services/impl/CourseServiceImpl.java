package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.entities.Category;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.mappers.CategoryMapper;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.services.CourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseOverviewResponse getCourseOverview(UUID id) {
        try {
            CourseRepository.CourseOverview courseOverview = courseRepository.getOverviewById(id);
            if(Objects.isNull(courseOverview)){
                throw new NotFoundException("Not found in database");
            }
            return CourseMapper.convertToOverviewResponse(courseOverview);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

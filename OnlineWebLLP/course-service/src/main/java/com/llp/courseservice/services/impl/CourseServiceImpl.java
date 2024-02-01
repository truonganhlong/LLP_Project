package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.TagRepository;
import com.llp.courseservice.services.CourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;
    @Override
    public CourseOverviewResponse getCourseOverview(String id) {
        try {
            CourseRepository.CourseOverview courseOverview = courseRepository.getOverviewById(id);
            if(Objects.isNull(courseOverview)){
                throw new NotFoundException("Not found in database");
            }
            CourseOverviewResponse data = CourseMapper.convertToOverviewResponse(courseOverview);
            data.setTag(tagRepository.getTagNameByCourseId(data.getId()));
            return data;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

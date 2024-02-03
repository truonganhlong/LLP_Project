package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Course.CourseCardJpql;
import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.Course.CourseFilter;
import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUserResponse;
import com.llp.courseservice.entities.ProminentTopic;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.mappers.ProminentTopicMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.TagRepository;
import com.llp.courseservice.services.CourseService;
import com.llp.courseservice.services.DiscountService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.sharedproject.sharedFunc.ReturnCourseFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;
    private final DiscountService discountService;
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

    @Override
    public List<CourseCardResponse> getAllProminentCourse(Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourse(paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
            for (var courseCardResponse:courseCardResponses) {
                //feign client user later
                courseCardResponse.setInstructor(null);
                courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
            }
            return courseCardResponses;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllProminentCourseBySubCategoryId(int subCategoryId, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourseBySubCategoryId(subCategoryId, paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
            for (var courseCardResponse:courseCardResponses) {
                //feign client user later
                courseCardResponse.setInstructor(null);
                courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
            }
            return courseCardResponses;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllProminentCourseByCategoryId(int categoryId, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourseByCategoryId(categoryId, paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
            for (var courseCardResponse:courseCardResponses) {
                //feign client user later
                courseCardResponse.setInstructor(null);
                courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
            }
            return courseCardResponses;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllCourseByTopicId(int topicId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter) {
        try {
            Sort sortByProperties = Sort.by(Sort.Direction.DESC, sortBy);
            Pageable paging = PageRequest.of(pageNo, pageSize, sortByProperties);
            List<CourseCardJpql> courseCards = courseRepository.getAllCourseByTopicId(topicId,paging);
            return filterCourse(filter, courseCards);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllCourseBySubCategoryId(int subCategoryId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter) {
        try {
            Sort sortByProperties = Sort.by(Sort.Direction.DESC, sortBy);
            Pageable paging = PageRequest.of(pageNo, pageSize, sortByProperties);
            List<CourseCardJpql> courseCards = courseRepository.getAllCourseBySubCategoryId(subCategoryId,paging);
            return filterCourse(filter, courseCards);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
    @Override
    public List<CourseCardResponse> getAllCourseByCategoryId(int categoryId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter) {
        try {
            Sort sortByProperties = Sort.by(Sort.Direction.DESC, sortBy);
            Pageable paging = PageRequest.of(pageNo, pageSize, sortByProperties);
            List<CourseCardJpql> courseCards = courseRepository.getAllCourseByCategoryId(categoryId,paging);
            return filterCourse(filter, courseCards);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
    // ----------------------------------------------------------------------------------------------------------------------
    private List<CourseCardResponse> filterCourse(CourseFilter filter, List<CourseCardJpql> courseCards) {
        if(filter.getRatingFilter() != null){
            courseCards = courseCards.stream().filter(x -> x.getRating() >= ReturnCourseFilter.returnRatingFilter(filter.getRatingFilter())).collect(Collectors.toList());
        }
        if(filter.getDurationFilter() != null){
            Integer min = ReturnCourseFilter.returnDurationFilter(filter.getDurationFilter()).get(0);
            Integer max = ReturnCourseFilter.returnDurationFilter(filter.getDurationFilter()).get(1);
            if(max != null){
                courseCards = courseCards.stream().filter(x -> x.getDuration() > min && x.getDuration() <= max).collect(Collectors.toList());
            }
            else {
                courseCards = courseCards.stream().filter(x -> x.getDuration() > min).collect(Collectors.toList());
            }
        }
        if(filter.getTopicFilter() != null){
            courseCards = courseCards.stream().filter(x -> x.getTopicId() == filter.getTopicFilter().longValue()).collect(Collectors.toList());
        }
        if(filter.getSubCategoryFilter() != null){
            courseCards = courseCards.stream().filter(x -> x.getSubCategoryId() == filter.getSubCategoryFilter().longValue()).collect(Collectors.toList());
        }
        if(filter.getLevelFilter() != null){
            courseCards = courseCards.stream().filter(x -> x.getLevelId() == filter.getLevelFilter().longValue()).collect(Collectors.toList());
        }
        if(filter.getLanguageFilter() != null){
            courseCards = courseCards.stream().filter(x -> x.getLanguageId() == filter.getLanguageFilter().longValue()).collect(Collectors.toList());
        }
        if(filter.getPriceFilter() != null){
            if(filter.getPriceFilter() == 1){
                courseCards = courseCards.stream().filter(x -> x.getPrice() != 0).collect(Collectors.toList());
            }
            if(filter.getPriceFilter() == 2){
                courseCards = courseCards.stream().filter(x -> x.getPrice() == 0).collect(Collectors.toList());
            }
        }
        List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardJpqlResponse).collect(Collectors.toList());
        for (var courseCardResponse:courseCardResponses) {
            //feign client user later
            courseCardResponse.setInstructor(null);
            courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
        }
        return courseCardResponses;
    }
}

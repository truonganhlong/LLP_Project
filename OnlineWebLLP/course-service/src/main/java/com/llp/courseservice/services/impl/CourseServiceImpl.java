package com.llp.courseservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.clients.dtos.InstructorResponse;
import com.llp.courseservice.dtos.Course.*;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.repositories.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final String root = System.getProperty("user.dir") + "/shared-project/src/main/resources/static/";
    private static final String directory = "images/course/";
    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;
    private final DiscountService discountService;
    private final LanguageRepository languageRepository;
    private final LevelRepository levelRepository;
    private final CourseTopicRepository courseTopicRepository;
    private final UserClient userClient;
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
                InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
                courseCardResponse.setInstructor(instructor.getFullname());
                courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
            }
            return courseCardResponses;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllProminentCourseByTopicId(int topicId, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseRepository.CourseCard> courseCards = courseRepository.getAllProminentCourseByTopicId(topicId, paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardResponse).collect(Collectors.toList());
            for (var courseCardResponse:courseCardResponses) {
                InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
                courseCardResponse.setInstructor(instructor.getFullname());
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
                InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
                courseCardResponse.setInstructor(instructor.getFullname());
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
                InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
                courseCardResponse.setInstructor(instructor.getFullname());
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

    @Override
    public void updateToProminent(String id) {
        try {
            Course course = courseRepository.getById(UUID.fromString(id));
            if(Objects.isNull(course)){
                throw new NotFoundException("Not found in database");
            }
            course.setProminent(!course.isProminent());
            if(course.isStatus() == false){
                course.setStatus(true);
            }
            courseRepository.save(course);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateStatus(String id) {
        try {
            Course course = courseRepository.getById(UUID.fromString(id));
            if(Objects.isNull(course)){
                throw new NotFoundException("Not found in database");
            }
            course.setStatus(!course.isStatus());
            courseRepository.save(course);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(String id) {
        try {
            Course course = courseRepository.getById(UUID.fromString(id));
            if(Objects.isNull(course)){
                throw new NotFoundException("Not found in database");
            }
            courseRepository.delete(course);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> searchCourseByName(String keyword, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter) {
        try {
            Sort sortByProperties = Sort.by(Sort.Direction.DESC, sortBy);
            Pageable paging = PageRequest.of(pageNo, pageSize, sortByProperties);
            List<CourseCardJpql> courseCards = courseRepository.searchCourseByName(keyword,paging);
            return filterCourse(filter, courseCards);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public String create(CourseCreateRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Course course = Course.builder()
                    .name(request.getName())
                    .target(objectMapper.writeValueAsString(request.getTarget()))
                    .requirement(objectMapper.writeValueAsString(request.getRequirement()))
                    .forWho(objectMapper.writeValueAsString(request.getForWho()))
                    .description(request.getDescription())
                    .overview(request.getOverview())
                    .language(languageRepository.getById(request.getLanguageId()))
                    .level(levelRepository.getById(request.getLevelId()))
                    .promoVideoLink(request.getPromoVideoLink())
                    .price(request.getPrice())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .createdBy(request.getCreatedBy())
                    .status(false)
                    .build();
            insertImage(course, request.getImageLink());
            for (var topicId: request.getTopic()) {
                courseTopicRepository.create(String.valueOf(course.getId()), topicId);
            }
            return String.valueOf(course.getId());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateDuration(String id, int duration) {
        try {
            Course course = courseRepository.getById(UUID.fromString(id));
            if(Objects.isNull(course)){
                throw new NotFoundException("Not found in database");
            }
            int durationByMinutes = duration/60;
            course.setDuration(course.getDuration() + durationByMinutes);
            courseRepository.save(course);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseTeacherResponse> getByTeacher(int createdBy) {
        try {
            List<Course> list = courseRepository.getByCreatedBy(createdBy);
            List<CourseTeacherResponse> data = new ArrayList<>();
            for (Course course : list) {
                CourseTeacherResponse courseTeacherResponse = CourseMapper.convertToTeacherResponse(course);
                courseTeacherResponse.setLevel(course.getLevel().getName());
                courseTeacherResponse.setLanguage(course.getLanguage().getName());
                data.add(courseTeacherResponse);
            }
            return data;
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
            InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
            courseCardResponse.setInstructor(instructor.getFullname());
            courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
        }
        return courseCardResponses;
    }
    private void insertImage(Course course, MultipartFile imageLink) {
        Path filePath = Paths.get(root, directory, imageLink.getOriginalFilename());
        try {
            imageLink.transferTo(new File(String.valueOf(filePath)));
            course.setImageLink(imageLink.getOriginalFilename());
            courseRepository.save(course);
        } catch (IOException e) {
            throw new NotFoundException("Not found file");
        }
    }
}

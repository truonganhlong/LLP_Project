package com.llp.courseservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.clients.dtos.InstructorResponse;
import com.llp.courseservice.dtos.Category.CategoryNameResponse;
import com.llp.courseservice.dtos.Course.*;
import com.llp.courseservice.dtos.Lecture.LecturePreviewResponse;
import com.llp.courseservice.dtos.Section.SectionDetailResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.entities.Lecture;
import com.llp.courseservice.mappers.CourseMapper;
import com.llp.courseservice.mappers.LectureMapper;
import com.llp.courseservice.repositories.*;
import com.llp.courseservice.services.CourseService;
import com.llp.courseservice.services.DiscountService;
import com.llp.courseservice.services.LectureService;
import com.llp.courseservice.services.SectionService;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.llp.sharedproject.sharedFunc.SharedFunction.convertStringToList;

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
    private final TopicRepository topicRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final LectureRepository lectureRepository;
    private final SectionService sectionService;
    private final LectureService lectureService;
    private final LastViewCourseRepository lastViewCourseRepository;
    private final UserClient userClient;
    @Override
    public CourseOverviewResponse getCourseOverview(String id) {
        try {
            CourseRepository.CourseOverview courseOverview = courseRepository.getOverviewById(id);
            if(Objects.isNull(courseOverview)){
                throw new NotFoundException("Not found in database");
            }
            CourseOverviewResponse data = CourseMapper.convertToOverviewResponse(courseOverview);
            data.setTag(tagRepository.getTagNameByCourseId(String.valueOf(data.getId())));
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

    @Override
    public CourseCardResponse getCourseCardById(String courseId) {
        try {
            CourseRepository.CourseCard courseCard = courseRepository.getCourseCardById(courseId);
            CourseCardResponse courseCardResponse = CourseMapper.convertToCardResponse(courseCard);
            InstructorResponse instructor = userClient.getInstructorInformation(courseCardResponse.getUserId());
            courseCardResponse.setInstructor(instructor.getFullname());
            courseCardResponse.setDiscountPrice(discountService.returnDiscountPrice(courseCardResponse.getId().toString()));
            return courseCardResponse;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public CourseDetailResponse getCourseDetail(String courseId, String authorizationHeader) {
        try {
            CourseDetailJpql courseDetailJpql = courseRepository.getCourseDetail(UUID.fromString(courseId));
            if(Objects.isNull(courseDetailJpql)){
                throw new NotFoundException("Not found in database");
            }
            CategoryNameResponse categoryNameResponse = CategoryNameResponse.builder()
                    .id(courseDetailJpql.getCategoryId())
                    .name(categoryRepository.getById(courseDetailJpql.getCategoryId()).getName())
                    .build();
            SubCategoryUserResponse subCategoryUserResponse = SubCategoryUserResponse.builder()
                    .id(courseDetailJpql.getSubCategoryId())
                    .name(subCategoryRepository.getById(courseDetailJpql.getSubCategoryId()).getName())
                    .build();
            TopicNameResponse topicNameResponse = TopicNameResponse.builder()
                    .id(courseDetailJpql.getTopicId())
                    .name(topicRepository.getById(courseDetailJpql.getTopicId()).getName())
                    .build();
            // convert string to list
            List<String> forWhoList = convertStringToList(courseDetailJpql.getForWho());
            List<String> requirementList = convertStringToList(courseDetailJpql.getRequirement());
            List<String> targetList = convertStringToList(courseDetailJpql.getTarget());

            // return the month by word
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDateTimeCreatedAt = courseDetailJpql.getCreatedAt().format(formatter);
            String formattedDateTimeUpdatedAt = courseDetailJpql.getUpdatedAt().format(formatter);
            //convert minute to hour
            String duration = "";
            if(courseDetailJpql.getDuration() < 60){
                duration = String.valueOf(courseDetailJpql.getDuration()) + " total seconds";
            }
            else if (courseDetailJpql.getDuration() < 3600){
                duration = String.valueOf(courseDetailJpql.getDuration()/60) + " total minutes";
            }
            else {
                duration = String.valueOf(courseDetailJpql.getDuration()/3600) + " total hours";
            }
            //get instructor
            InstructorResponse instructor = userClient.getInstructorInformation(courseDetailJpql.getCreatedBy().intValue());
            CourseDetailResponse data = CourseDetailResponse.builder()
                    .id(courseDetailJpql.getId())
                    .category(categoryNameResponse)
                    .subCategory(subCategoryUserResponse)
                    .topic(topicNameResponse)
                    .name(courseDetailJpql.getName())
                    .description(courseDetailJpql.getDescription())
                    .overview(courseDetailJpql.getOverview())
                    .forWho(forWhoList)
                    .requirement(requirementList)
                    .target(targetList)
                    .imageLink(courseDetailJpql.getImageLink())
                    .rating(courseDetailJpql.getRating())
                    .ratingNum(courseDetailJpql.getRatingNum())
                    .saleNum(courseDetailJpql.getSaleNum())
                    .price(courseDetailJpql.getPrice())
                    .discountPrice(discountService.returnDiscountPrice(courseDetailJpql.getId().toString()))
                    .duration(duration)
                    .createdAt(formattedDateTimeCreatedAt)
                    .updatedAt(formattedDateTimeUpdatedAt)
                    .instructorResponse(instructor)
                    .language(languageRepository.getById(courseDetailJpql.getLanguageId()).getName())
                    .level(levelRepository.getById(courseDetailJpql.getLevelId()).getName())
                    .tag(tagRepository.getTagNameByCourseId(String.valueOf(courseDetailJpql.getId())))
                    .build();
            List<SectionDetailResponse> sections = sectionService.getAllSectionByCourse(String.valueOf(data.getId()));
            data.setSectionDetailResponses(sections);
            data.setSectionNum(sections.size());
            for (var section:sections) {
                if(authorizationHeader == null || userClient.isHaveAuthenticationCourse(authorizationHeader, String.valueOf(data.getId())) == false){
                    section.setLectureDetailBeforePurchasedResponses(lectureService.getAllLectureBySectionBeforePurchased(section.getId().intValue()));
                    section.setLectureNum(lectureService.getAllLectureBySectionBeforePurchased(section.getId().intValue()).size());
                    int sectionDuration = 0;
                    for (var lecture:section.getLectureDetailBeforePurchasedResponses()) {
                        sectionDuration += lecture.getDuration();
                    }
                    section.setDuration(secondsToMinutes(sectionDuration));
                }
                else {
                    section.setLectureDetailAfterPurchasedResponses(lectureService.getAllLectureBySectionAfterPurchased(section.getId().intValue()));
                    section.setLectureNum(lectureService.getAllLectureBySectionAfterPurchased(section.getId().intValue()).size());
                    int sectionDuration = 0;
                    for (var lecture:section.getLectureDetailAfterPurchasedResponses()) {
                        sectionDuration += lecture.getDuration();
                    }
                    section.setDuration(secondsToMinutes(sectionDuration));
                }
            }
            if(authorizationHeader != null){
                //add last view course
                String lastViewCourseId = lastViewCourseRepository.getLastViewCourse(userClient.returnUserId(authorizationHeader));
                if(lastViewCourseId == null){
                    lastViewCourseRepository.create(String.valueOf(data.getId()), userClient.returnUserId(authorizationHeader));
                } else {
                    lastViewCourseRepository.update(String.valueOf(data.getId()), userClient.returnUserId(authorizationHeader));
                }
            }
            return data;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateSaleNum(String courseId) {
        try {
            Course course = courseRepository.getById(UUID.fromString(courseId));
            if(Objects.isNull(course)){
                throw new NotFoundException("Not found in database");
            }
            course.setSaleNum(course.getSaleNum() + 1);
            courseRepository.save(course);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getByCategoryByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize) {
        try {
            int userId = userClient.returnUserId(authorizationHeader);
            String lastViewCourseId = lastViewCourseRepository.getLastViewCourse(userId);
            if(lastViewCourseId.isEmpty()){
                return null;
            } else {
                CourseDetailJpql courseDetailJpql = courseRepository.getCourseDetail(UUID.fromString(lastViewCourseId));
                if(Objects.isNull(courseDetailJpql)){
                    throw new NotFoundException("Not found in database");
                }
                int categoryId = courseDetailJpql.getCategoryId().intValue();
                return getAllCourseByCategoryId(categoryId, pageNo, pageSize, "ratingNum", null);
            }
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getBySubCategoryByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize) {
        try {
            int userId = userClient.returnUserId(authorizationHeader);
            String lastViewCourseId = lastViewCourseRepository.getLastViewCourse(userId);
            if(lastViewCourseId.isEmpty()){
                return null;
            } else {
                CourseDetailJpql courseDetailJpql = courseRepository.getCourseDetail(UUID.fromString(lastViewCourseId));
                if(Objects.isNull(courseDetailJpql)){
                    throw new NotFoundException("Not found in database");
                }
                int subCategoryId = courseDetailJpql.getSubCategoryId().intValue();
                return getAllCourseBySubCategoryId(subCategoryId, pageNo, pageSize, "ratingNum", null);
            }
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getByTopicByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize) {
        try {
            int userId = userClient.returnUserId(authorizationHeader);
            String lastViewCourseId = lastViewCourseRepository.getLastViewCourse(userId);
            if(lastViewCourseId.isEmpty()){
                return null;
            } else {
                CourseDetailJpql courseDetailJpql = courseRepository.getCourseDetail(UUID.fromString(lastViewCourseId));
                if(Objects.isNull(courseDetailJpql)){
                    throw new NotFoundException("Not found in database");
                }
                int topicId = courseDetailJpql.getTopicId().intValue();
                return getAllCourseByTopicId(topicId, pageNo, pageSize, "ratingNum", null);
            }
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllCourseByCreatedBy(int createdBy, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseCardJpql> courseCards = courseRepository.getAllCourseByCreatedBy(createdBy,paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardJpqlResponse).collect(Collectors.toList());
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
    public CoursePreviewResponse getAllCoursePreview(String courseId) {
        try {
            Course course = courseRepository.getById(UUID.fromString(courseId));
            List<Lecture> lectures = lectureRepository.getAllPreviewLecture(UUID.fromString(courseId));
            List<LecturePreviewResponse> lecturePreviewResponses = lectures.stream().map(LectureMapper :: convertToPreviewResponse).collect(Collectors.toList());
            CoursePreviewResponse data = CoursePreviewResponse.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .lectures(lecturePreviewResponses)
                    .build();
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getAllCourseCardNotApprove(Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<CourseCardJpql> courseCards = courseRepository.getAllCourseCardNotApprove(paging);
            List<CourseCardResponse> courseCardResponses = courseCards.stream().map(CourseMapper::convertToCardJpqlResponse).collect(Collectors.toList());
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

    // ----------------------------------------------------------------------------------------------------------------------
    private List<CourseCardResponse> filterCourse(CourseFilter filter, List<CourseCardJpql> courseCards) {
        if(!Objects.isNull(filter)){
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = imageLink.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = formattedDateTime + randomUUID + fileExtension;
        Path filePath = Paths.get(root, directory, newFilename);
        try {
            imageLink.transferTo(new File(String.valueOf(filePath)));
            course.setImageLink(newFilename);
            courseRepository.save(course);
        } catch (IOException e) {
            throw new NotFoundException("Not found file");
        }
    }

    private static String secondsToMinutes(int seconds) {
        int minutes = seconds / 60;
        if(minutes < 60){
            return String.format("%d min", minutes);
        } else {
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return String.format("%dhr %02d min", hours, remainingMinutes);
        }
    }
}

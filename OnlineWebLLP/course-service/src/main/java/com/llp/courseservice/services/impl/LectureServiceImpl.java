package com.llp.courseservice.services.impl;

import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.clients.dtos.YourLectureResponse;
import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import com.llp.courseservice.dtos.Lecture.LectureDetailAfterPurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureDetailBeforePurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureStudiedResponse;
import com.llp.courseservice.entities.Lecture;
import com.llp.courseservice.entities.Section;
import com.llp.courseservice.mappers.LectureMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.LectureRepository;
import com.llp.courseservice.repositories.SectionRepository;
import com.llp.courseservice.services.CourseService;
import com.llp.courseservice.services.LectureService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final UserClient userClient;
    @Override
    public void create(LectureCreateRequest request, int sectionId) {
        try {
            Lecture lecture = Lecture.builder()
                    .name(request.getName())
                    .goal(request.getGoal())
                    .link(request.getLink())
                    .duration(request.getDuration())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .createdBy(request.getCreatedBy())
                    .isFree(request.isFree())
                    .status(true)
                    .section(sectionRepository.getById(sectionId))
                    .build();
            lectureRepository.save(lecture);
            lectureRepository.updateCourseDuration(lecture.getDuration(), String.valueOf(sectionRepository.getById(sectionId).getCourse().getId()), LocalDateTime.now());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateStatus(int id) {
        try {
            Lecture lecture = lectureRepository.getById(id);
            if(Objects.isNull(lecture)){
                throw new NotFoundException("Not found in database");
            }
            lecture.setStatus(!lecture.isStatus());
            lectureRepository.save(lecture);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<LectureDetailBeforePurchasedResponse> getAllLectureBySectionBeforePurchased(int sectionId) {
        try {
            List<Lecture> lectures = lectureRepository.getAllBySection(sectionId);
            List<LectureDetailBeforePurchasedResponse> data = lectures.stream().map(LectureMapper :: convertToDetailBeforePurchasedResponse).collect(Collectors.toList());
            for (var lecture:data) {
                if(lecture.isFree() == false){
                    lecture.setLink(null);
                }
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<LectureDetailAfterPurchasedResponse> getAllLectureBySectionAfterPurchased(int sectionId) {
        try {
            List<Lecture> lectures = lectureRepository.getAllBySection(sectionId);
            List<LectureDetailAfterPurchasedResponse> data = lectures.stream().map(LectureMapper :: convertToDetailAfterPurchasedResponse).collect(Collectors.toList());
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public LectureDetailAfterPurchasedResponse getById(int id, String authorizationHeader, String courseId) {
        try {
            Lecture lecture = lectureRepository.getById(id);
            LectureDetailAfterPurchasedResponse data = LectureMapper.convertToDetailAfterPurchasedResponse(lecture);
            if(userClient.isHaveAuthenticationCourse(authorizationHeader, courseId)){
                if(userClient.countByUserAndCourse(authorizationHeader,courseId) != 0){
                    userClient.update(authorizationHeader,courseId,id);
                } else {
                    userClient.create(authorizationHeader,courseId,id);
                }
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<LectureStudiedResponse> getAllLectureStudied(String authorizationHeader) {
        try {
            List<YourLectureResponse> yourLectures = userClient.getAllYourLecture(authorizationHeader);
            List<LectureStudiedResponse> data = new ArrayList<>();
            for (var yourLecture:yourLectures) {
                LectureStudiedResponse lectureStudiedResponse = new LectureStudiedResponse();
                lectureStudiedResponse.setCourseId(yourLecture.getCourseId());
                lectureStudiedResponse.setLectureId(yourLecture.getLectureId());
                lectureStudiedResponse.setCourseName(lectureRepository.getById(yourLecture.getLectureId()).getSection().getCourse().getName());
                lectureStudiedResponse.setLectureName(lectureRepository.getById(yourLecture.getLectureId()).getName());
                lectureStudiedResponse.setLink(lectureRepository.getById(yourLecture.getLectureId()).getLink());
                data.add(lectureStudiedResponse);
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

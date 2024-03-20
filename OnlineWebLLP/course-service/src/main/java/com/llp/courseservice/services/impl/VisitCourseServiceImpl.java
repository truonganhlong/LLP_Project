package com.llp.courseservice.services.impl;

import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.dtos.VisitCourse.VisitCourseResponse;
import com.llp.courseservice.entities.VisitCourse;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.VisitCourseRepository;
import com.llp.courseservice.services.VisitCourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitCourseServiceImpl implements VisitCourseService {
    private final VisitCourseRepository visitCourseRepository;
    private final CourseRepository courseRepository;
    private final UserClient userClient;
    @Override
    public void create(String authorizationHeader, String courseId) {
        try {
            VisitCourse visitCourse;
            if(authorizationHeader != null){
                visitCourse = VisitCourse.builder()
                        .userId((long) userClient.returnUserId(authorizationHeader))
                        .visitAt(LocalDateTime.now())
                        .course(courseRepository.getById(UUID.fromString(courseId)))
                        .build();
            } else {
                visitCourse = VisitCourse.builder()
                        .userId(0L)
                        .visitAt(LocalDateTime.now())
                        .course(courseRepository.getById(UUID.fromString(courseId)))
                        .build();
            }
            visitCourseRepository.save(visitCourse);
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }

    @Override
    public VisitCourseResponse getAllVisitByCourse(String courseId, String day) {
        try {
            return VisitCourseResponse.builder()
                    .anonymousVisit(visitCourseRepository.returnAnonymousVisit(courseId,day))
                    .userVisit(visitCourseRepository.returnUserVisit(courseId,day))
                    .totalVisit(visitCourseRepository.returnAnonymousVisit(courseId,day) + visitCourseRepository.returnUserVisit(courseId,day))
                    .build();
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }
}

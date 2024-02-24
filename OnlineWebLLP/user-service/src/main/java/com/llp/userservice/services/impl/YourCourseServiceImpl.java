package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.clients.CourseClient;
import com.llp.userservice.clients.dtos.CourseCardResponse;
import com.llp.userservice.entities.YourCourse;
import com.llp.userservice.repositories.YourCourseRepository;
import com.llp.userservice.services.YourCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class YourCourseServiceImpl implements YourCourseService {
    private final YourCourseRepository yourCourseRepository;
    private final CourseClient courseClient;
    @Override
    public void create(String courseId, int userId, String orderId) {
        try {
            yourCourseRepository.create(courseId,userId,orderId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public boolean isHaveAuthenticationToCourse(int userId, String courseId) {
        try {
            YourCourse yourCourse = yourCourseRepository.getByUserAndCourse(userId, courseId);
            if(Objects.isNull(yourCourse)){
                return false;
            } else {
                return true;
            }
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getYourCourse(int userId) {
        try {
            List<YourCourse> yourCourses = yourCourseRepository.getByUser(userId);
            List<CourseCardResponse> data = new ArrayList<>();
            for (var yourCourse:yourCourses) {
                CourseCardResponse courseCardResponse = courseClient.getCourseCardById(yourCourse.getId().getCourseId());
                data.add(courseCardResponse);
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

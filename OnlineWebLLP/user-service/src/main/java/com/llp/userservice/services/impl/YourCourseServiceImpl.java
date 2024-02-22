package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.repositories.YourCourseRepository;
import com.llp.userservice.services.YourCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YourCourseServiceImpl implements YourCourseService {
    private final YourCourseRepository yourCourseRepository;
    @Override
    public void create(String courseId, int userId, String orderId) {
        try {
            yourCourseRepository.create(courseId,userId,orderId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.dtos.yourLecture.YourLectureResponse;
import com.llp.userservice.entities.YourLecture;
import com.llp.userservice.mappers.YourLectureMapper;
import com.llp.userservice.repositories.YourLectureRepository;
import com.llp.userservice.services.YourLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YourLectureServiceImpl implements YourLectureService {
    private final YourLectureRepository yourLectureRepository;
    @Override
    public int countByUserAndCourse(int userId, String courseId) {
        try {
            return (int) yourLectureRepository.countByUserAndCourse(userId,courseId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(int userId, String courseId, int lectureId) {
        try {
            yourLectureRepository.create(userId,courseId,lectureId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int userId, String courseId, int lectureId) {
        try {
            yourLectureRepository.update(userId, courseId, lectureId);
        } catch(Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<YourLectureResponse> getAllYourLecture(int userId) {
        try {
            List<YourLecture> yourLectures = yourLectureRepository.getByUser(userId);
            return yourLectures.stream().map(YourLectureMapper :: convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

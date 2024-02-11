package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import com.llp.courseservice.entities.Lecture;
import com.llp.courseservice.entities.Section;
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
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final CourseService courseService;
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
            courseService.updateDuration(String.valueOf(sectionRepository.getById(sectionId).getCourse().getId()), lecture.getDuration());
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
}

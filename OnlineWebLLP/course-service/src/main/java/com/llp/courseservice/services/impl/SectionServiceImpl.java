package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.entities.Section;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.SectionRepository;
import com.llp.courseservice.services.LectureService;
import com.llp.courseservice.services.SectionService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.llp.sharedproject.sharedFunc.SharedFunction.convertStringToList;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;

    @Override
    public int create(SectionCreateRequest request, String courseId) {
        try {
            Section section = Section.builder()
                    .name(request.getName())
                    .course(courseRepository.getById(UUID.fromString(courseId)))
                    .build();
            sectionRepository.save(section);
            return section.getId().intValue();
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

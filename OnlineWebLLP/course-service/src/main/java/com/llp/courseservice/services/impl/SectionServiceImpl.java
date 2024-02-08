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

import static com.llp.sharedproject.sharedFunc.SharedFunction.convertStringToList;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final LectureService lectureService;

    @Override
    public void create(SectionCreateRequest request, Course course) {
        try {
            Section section = Section.builder()
                    .name(request.getName())
                    .course(course)
                    .build();
            sectionRepository.save(section);
//            List<LectureCreateRequest> lectureCreateRequests = request.getLectureRequest();
//            for(LectureCreateRequest lectureCreateRequest : lectureCreateRequests){
//                lectureService.create(lectureCreateRequest, section);
//            }
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

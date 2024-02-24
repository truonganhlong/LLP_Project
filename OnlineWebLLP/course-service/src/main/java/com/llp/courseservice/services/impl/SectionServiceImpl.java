package com.llp.courseservice.services.impl;

import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.dtos.Section.SectionDetailResponse;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.entities.Section;
import com.llp.courseservice.mappers.SectionMapper;
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
import java.util.stream.Collectors;

import static com.llp.sharedproject.sharedFunc.SharedFunction.convertStringToList;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final LectureService lectureService;
    private final UserClient userClient;

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

    @Override
    public List<SectionDetailResponse> getAllSectionByCourse(String courseId) {
        try {
            List<Section> sections = sectionRepository.getAllByCourse(courseId);
            List<SectionDetailResponse> data = sections.stream().map(SectionMapper :: convertToDetailResponse).collect(Collectors.toList());
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
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

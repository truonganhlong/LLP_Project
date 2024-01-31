package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.repositories.CourseRepository;

import java.time.format.DateTimeFormatter;

public class CourseMapper {
    public static CourseOverviewResponse convertToOverviewResponse(CourseRepository.CourseOverview courseOverview){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDateTime = courseOverview.getUpdatedAt().format(formatter);
        int duration = courseOverview.getDuration()/60;
        return CourseOverviewResponse.builder()
                .id(courseOverview.getId())
                .name(courseOverview.getName())
                .updatedAt(formattedDateTime)
                .duration(duration)
                .level(courseOverview.getLevel())
                .overview(courseOverview.getOverview())

                .build();
    }

    public static CourseCardResponse convertToCardResponse(CourseRepository.CourseCard courseCard){
        return CourseCardResponse.builder()
                .id(courseCard.getId())
                .imageLink(courseCard.getImageLink())
                .name(courseCard.getName())
                .userId(courseCard.getCreatedBy())
                .rating(courseCard.getRating())
                .ratingNum(courseCard.getRatingNum())
                .price(courseCard.getPrice())
                .build();
    }
}

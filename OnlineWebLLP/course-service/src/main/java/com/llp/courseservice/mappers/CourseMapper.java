package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.repositories.CourseRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseMapper {
    public static CourseOverviewResponse convertToOverviewResponse(CourseRepository.CourseOverview courseOverview){
        // return the month by word
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDateTime = courseOverview.getUpdatedAt().format(formatter);
        //convert minute to hour
        int duration = courseOverview.getDuration()/60;
        //convert string target to List<String> and get only 3 elements
        String[] elements = courseOverview.getTarget().split(",");
        List<String> targetList = new ArrayList<>(Arrays.asList(elements));
        targetList = targetList.subList(0,3);
        return CourseOverviewResponse.builder()
                .id(courseOverview.getId())
                .name(courseOverview.getName())
                .updatedAt(formattedDateTime)
                .duration(duration)
                .level(courseOverview.getLevel())
                .overview(courseOverview.getOverview())
                .target(targetList)
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

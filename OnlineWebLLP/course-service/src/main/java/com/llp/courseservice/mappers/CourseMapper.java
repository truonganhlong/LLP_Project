package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Course.CourseCardJpql;
import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.repositories.CourseRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CourseMapper {
    public static CourseOverviewResponse convertToOverviewResponse(CourseRepository.CourseOverview courseOverview){
        // return the month by word
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDateTime = courseOverview.getUpdatedAt().format(formatter);
        //convert minute to hour
        String duration = "";
        if(courseOverview.getDuration() < 60){
            duration = String.valueOf(courseOverview.getDuration()) + " total minutes";
        }
        else {
            duration = String.valueOf(courseOverview.getDuration()/60) + " total hours";
        }
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

    public static CourseCardResponse convertToCardJpqlResponse(CourseCardJpql courseCard){
        return CourseCardResponse.builder()
                .id(courseCard.getId())
                .imageLink(courseCard.getImageLink())
                .name(courseCard.getName())
                .userId(courseCard.getCreatedBy().intValue())
                .rating(courseCard.getRating())
                .ratingNum(courseCard.getRatingNum())
                .price(courseCard.getPrice())
                .build();
    }
}

package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Course.CourseCardJpql;
import com.llp.courseservice.dtos.Course.CourseCardResponse;
import com.llp.courseservice.dtos.Course.CourseOverviewResponse;
import com.llp.courseservice.dtos.Course.CourseTeacherResponse;
import com.llp.courseservice.entities.Course;
import com.llp.courseservice.repositories.CourseRepository;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.llp.sharedproject.sharedFunc.SharedFunction.convertStringToList;

public class CourseMapper {
    public static CourseOverviewResponse convertToOverviewResponse(CourseRepository.CourseOverview courseOverview) throws IOException {
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

        List<String> targetList = convertStringToList(courseOverview.getTarget());
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

    public static CourseTeacherResponse convertToTeacherResponse(Course course) throws IOException {
        List<String> forWhoList = convertStringToList(course.getForWho());
        List<String> requirementList = convertStringToList(course.getRequirement());
        List<String> targetList = convertStringToList(course.getTarget());
        // return the month by word
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDateTimeCreatedAt = course.getCreatedAt().format(formatter);
        String formattedDateTimeUpdatedAt = course.getUpdatedAt().format(formatter);
        //convert minute to hour
        String duration = "";
        if(course.getDuration() < 60){
            duration = String.valueOf(course.getDuration()) + " total minutes";
        }
        else {
            duration = String.valueOf(course.getDuration()/60) + " total hours";
        }
        return CourseTeacherResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .overview(course.getOverview())
                .forWho(forWhoList)
                .requirement(requirementList)
                .target(targetList)
                .imageLink(course.getImageLink())
                .promoVideoLink(course.getPromoVideoLink())
                .rating(course.getRating())
                .ratingNum(course.getRatingNum())
                .saleNum(course.getSaleNum())
                .price(course.getPrice())
                .duration(duration)
                .createdAt(formattedDateTimeCreatedAt)
                .updatedAt(formattedDateTimeUpdatedAt)
                .build();
    }

}

package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Lecture.LectureDetailAfterPurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureDetailBeforePurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LecturePreviewResponse;
import com.llp.courseservice.entities.Lecture;

import java.time.format.DateTimeFormatter;

public class LectureMapper {
    public static LectureDetailAfterPurchasedResponse convertToDetailAfterPurchasedResponse(Lecture lecture){
        return LectureDetailAfterPurchasedResponse.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .goal(lecture.getGoal())
                .link(lecture.getLink())
                .duration(lecture.getDuration())
                .durationToString(secondsToMinutes(lecture.getDuration()))
                .build();
    }

    public static LectureDetailBeforePurchasedResponse convertToDetailBeforePurchasedResponse(Lecture lecture){
        return LectureDetailBeforePurchasedResponse.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .link(lecture.getLink())
                .duration(lecture.getDuration())
                .durationToString(secondsToMinutes(lecture.getDuration()))
                .isFree(lecture.isFree())
                .build();
    }

    public static LecturePreviewResponse convertToPreviewResponse(Lecture lecture){
        return LecturePreviewResponse.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .link(lecture.getLink())
                .duration(secondsToMinutes(lecture.getDuration()))
                .build();
    }

    public static String secondsToMinutes(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        if(seconds < 3600){
            return String.format("%d:%02d", minutes, remainingSeconds);
        } else {
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return String.format("%d:%02d:%03d", hours, remainingMinutes, remainingSeconds);
        }
    }
}

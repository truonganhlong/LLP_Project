package com.llp.courseservice.dtos.Section;

import com.llp.courseservice.dtos.Lecture.LectureDetailAfterPurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureDetailBeforePurchasedResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SectionDetailResponse {
    private Long id;
    private String name;
    private int lectureNum;
    private String duration;
    private List<LectureDetailBeforePurchasedResponse> lectureDetailBeforePurchasedResponses;
    private List<LectureDetailAfterPurchasedResponse> lectureDetailAfterPurchasedResponses;
}

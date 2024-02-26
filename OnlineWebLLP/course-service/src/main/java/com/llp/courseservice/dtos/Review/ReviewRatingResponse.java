package com.llp.courseservice.dtos.Review;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewRatingResponse {
    private List<ReviewResponse> reviews;
    int rating5star;
    int rating4star;
    int rating3star;
    int rating2star;
    int rating1star;
}

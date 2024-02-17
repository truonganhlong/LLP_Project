package com.llp.courseservice.clients.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InstructorResponse {
    private Long id;
    private String fullname;
    private String headline;
    private String imageLink;
    private double rating;
    private int reviewNum;
    private int studentNum;
    private int courseNum;
    private String biography;
}

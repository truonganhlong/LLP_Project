package com.llp.userservice.dtos.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String fullname;
    private String headline;
    private String biography;
    private String twitterLink;
    private String facebookLink;
    private String linkedinLink;
    private String youtubeLink;
    private String imageLink;
}

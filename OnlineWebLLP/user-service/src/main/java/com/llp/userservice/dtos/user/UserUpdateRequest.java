package com.llp.userservice.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    @JsonProperty
    private String fullname;
    @JsonProperty
    private String headline;
    @JsonProperty
    private String biography;
    @JsonProperty
    private String twitterLink;
    @JsonProperty
    private String facebookLink;
    @JsonProperty
    private String linkedinLink;
    @JsonProperty
    private String youtubeLink;
}

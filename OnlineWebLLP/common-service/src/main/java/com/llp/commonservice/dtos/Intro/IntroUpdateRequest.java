package com.llp.commonservice.dtos.Intro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IntroUpdateRequest {
    @JsonProperty
    private String title;
    @JsonProperty
    private String content;
    @JsonProperty
    private MultipartFile imageLink;
    @JsonProperty
    private int introMapId;
}

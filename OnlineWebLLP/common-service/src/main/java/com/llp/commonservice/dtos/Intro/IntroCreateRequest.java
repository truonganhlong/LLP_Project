package com.llp.commonservice.dtos.Intro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IntroCreateRequest {
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    private String content;
    @JsonProperty(required = true)
    private MultipartFile imageLink;
    @JsonProperty(required = true)
    private int introMapId;
}

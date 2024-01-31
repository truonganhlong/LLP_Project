package com.llp.courseservice.dtos.Category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryCreateRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private MultipartFile imageLink;
}

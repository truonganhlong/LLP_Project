package com.llp.courseservice.dtos.Course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseCreateRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private int categoryId;
    @JsonProperty(required = true)
    private List<String> target;
    @JsonProperty(required = true)
    private List<String> requirement;
    @JsonProperty(required = true)
    private List<String> forWho;
    @JsonProperty(required = true)
    private String description;
    @JsonProperty(required = true)
    private String overview;
    @JsonProperty(required = true)
    private int languageId;
    @JsonProperty(required = true)
    private int levelId;
    @JsonProperty(required = true)
    private int subCategoryId;
    @JsonProperty(required = true)
    private List<Integer> topic;
    @JsonProperty(required = true)
    private MultipartFile imageLink;
    @JsonProperty(required = true)
    private double price;
    @JsonProperty(required = true)
    private Long createdBy;
}

package com.llp.courseservice.dtos.Course;

import com.llp.courseservice.clients.dtos.InstructorResponse;
import com.llp.courseservice.dtos.Category.CategoryNameResponse;
import com.llp.courseservice.dtos.Section.SectionDetailResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDetailResponse {
    private UUID id;
    private CategoryNameResponse category;
    private SubCategoryUserResponse subCategory;
    private TopicNameResponse topic;
    private String name;
    private String description;
    private String overview;
    private List<String> forWho;
    private List<String> requirement;
    private List<String> target;
    private String imageLink;
    private String promoVideoLink;
    private double rating;
    private int ratingNum;
    private int saleNum;
    private double price;
    private double discountPrice;
    private String duration;
    private String createdAt;
    private String updatedAt;
    private InstructorResponse instructorResponse;
    private String language;
    private String level;
    private List<String> tag;
    private int sectionNum;
    private int lectureNum;
    private List<SectionDetailResponse> sectionDetailResponses;
}

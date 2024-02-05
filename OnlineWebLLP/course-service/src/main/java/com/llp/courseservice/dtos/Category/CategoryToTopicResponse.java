package com.llp.courseservice.dtos.Category;

import com.llp.courseservice.dtos.SubCategory.SubCategoryToTopicResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CategoryToTopicResponse {
    private Long id;
    private String name;
    private List<SubCategoryToTopicResponse> subCategories;
}

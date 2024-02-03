package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Category.CategoryNameResponse;
import com.llp.courseservice.dtos.Category.CategoryAdminResponse;
import com.llp.courseservice.dtos.Category.CategoryUserResponse;
import com.llp.courseservice.entities.Category;
import com.llp.courseservice.repositories.CategoryRepository;

public class CategoryMapper {
    public static CategoryAdminResponse convertToAdminResponse(Category category){
        return CategoryAdminResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .imageLink(category.getImageLink())
                .status(category.isStatus())
                .build();
    }
    public static CategoryNameResponse convertToNameResponse(CategoryRepository.CategoryByName category){
        return CategoryNameResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategoryUserResponse convertToUserResponse(CategoryRepository.CategoryByUser category){
        return CategoryUserResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .imageLink(category.getImageLink())
                .build();
    }

}

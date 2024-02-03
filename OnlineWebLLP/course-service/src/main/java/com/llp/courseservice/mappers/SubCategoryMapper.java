package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.SubCategory.SubCategoryAdminResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;
import com.llp.courseservice.entities.SubCategory;
import com.llp.courseservice.repositories.SubCategoryRepository;

public class SubCategoryMapper {
    public static SubCategoryAdminResponse convertToAdminResponse(SubCategory subCategory){
        return  SubCategoryAdminResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .status(subCategory.isStatus())
                .build();
    }

    public static SubCategoryUserResponse convertToUserResponse(SubCategoryRepository.SubCategoryByName subCategory){
        return  SubCategoryUserResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }
}

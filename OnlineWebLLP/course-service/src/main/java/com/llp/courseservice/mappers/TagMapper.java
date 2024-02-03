package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Tag.TagResponse;
import com.llp.courseservice.entities.Tag;

public class TagMapper {
    public static TagResponse convertToResponse(Tag tag){
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}

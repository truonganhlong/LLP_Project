package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Section.SectionDetailResponse;
import com.llp.courseservice.entities.Section;

public class SectionMapper {
    public static SectionDetailResponse convertToDetailResponse(Section section){
        return SectionDetailResponse.builder()
                .id(section.getId())
                .name(section.getName())
                .build();
    }
}

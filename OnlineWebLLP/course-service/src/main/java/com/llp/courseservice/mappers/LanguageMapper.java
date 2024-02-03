package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Language.LanguageResponse;
import com.llp.courseservice.entities.Language;

public class LanguageMapper {
    public static LanguageResponse convertToResponse(Language language){
        return LanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }
}

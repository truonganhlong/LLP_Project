package com.llp.commonservice.mappers;

import com.llp.commonservice.dtos.Intro.IntroResponse;
import com.llp.commonservice.entities.Intro;

public class IntroMapper {
    public static IntroResponse convertToResponse(Intro intro){
        return IntroResponse.builder()
                .id(intro.getId())
                .title(intro.getTitle())
                .content(intro.getContent())
                .imageLink(intro.getImageLink())
                .status(intro.isStatus())
                .introMapId(intro.getIntroMap().getId())
                .build();
    }
}

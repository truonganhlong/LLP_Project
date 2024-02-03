package com.llp.commonservice.mappers;

import com.llp.commonservice.dtos.IntroMap.IntroMapResponse;
import com.llp.commonservice.entities.IntroMap;

public class IntroMapMapper {
    public static IntroMapResponse convertToResponse(IntroMap introMap){
        return IntroMapResponse.builder()
                .id(introMap.getId())
                .map(introMap.getMap())
                .build();
    }
}

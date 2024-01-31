package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Level.LevelResponse;
import com.llp.courseservice.entities.Level;

public class LevelMapper {
    public static LevelResponse convertToResponse(Level level){
        return LevelResponse.builder()
                .id(level.getId())
                .name(level.getName())
                .build();
    }
}

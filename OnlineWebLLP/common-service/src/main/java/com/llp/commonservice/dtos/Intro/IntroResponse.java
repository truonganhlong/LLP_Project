package com.llp.commonservice.dtos.Intro;

import com.llp.commonservice.dtos.IntroMap.IntroMapResponse;
import com.llp.commonservice.entities.IntroMap;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IntroResponse {
    private Long id;
    private String title;
    private String content;
    private String imageLink;
    private boolean status;
    private Long introMapId;
    private IntroMapResponse introMap;
}

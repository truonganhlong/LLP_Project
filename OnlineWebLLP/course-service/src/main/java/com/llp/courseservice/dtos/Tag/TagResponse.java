package com.llp.courseservice.dtos.Tag;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagResponse {
    private Long id;
    private String name;
}

package com.llp.courseservice.dtos.Category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryUserResponse {
    private Long id;
    private String name;
    private String imageLink;
}

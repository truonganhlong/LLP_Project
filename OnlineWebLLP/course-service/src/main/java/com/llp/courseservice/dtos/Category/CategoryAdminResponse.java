package com.llp.courseservice.dtos.Category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryAdminResponse {
    private Long id;
    private String name;
    private String imageLink;
    private boolean status;
}

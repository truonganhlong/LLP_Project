package com.llp.courseservice.dtos.SubCategory;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryAdminResponse {
    private Long id;
    private String name;
    private boolean status;
}

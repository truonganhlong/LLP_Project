package com.llp.userservice.dtos.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GoogleUserInfo {
    private String email;
    private String name;
    private String picture;
}

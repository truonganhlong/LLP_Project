package com.llp.userservice.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterGoogleRequest {
    @JsonProperty(required = true)
    private String fullName;
    @JsonProperty(required = true)
    private String email;
}

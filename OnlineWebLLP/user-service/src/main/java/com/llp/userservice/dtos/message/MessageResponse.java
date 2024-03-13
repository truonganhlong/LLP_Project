package com.llp.userservice.dtos.message;

import com.llp.userservice.dtos.user.UserResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageResponse {
    private Long id;
    private String message;
    private String createdAt;
    private int userReceiveId;
    private int userSendId;
}

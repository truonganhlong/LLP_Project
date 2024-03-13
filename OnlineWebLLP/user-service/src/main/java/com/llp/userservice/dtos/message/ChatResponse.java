package com.llp.userservice.dtos.message;

import com.llp.userservice.dtos.user.UserResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatResponse {
    private List<MessageResponse> message;
    private UserResponse userReceive;
    private UserResponse userSend;
}

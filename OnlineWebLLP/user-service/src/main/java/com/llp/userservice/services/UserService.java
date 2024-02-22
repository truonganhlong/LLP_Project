package com.llp.userservice.services;

import com.llp.userservice.dtos.user.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse loginViaForm(AuthenticationRequest request);
    AuthenticationResponse registerAsAdmin(RegisterRequest request);

    AuthenticationResponse loginViaFormAsAdmin(AuthenticationRequest request);

    UserResponse getUserInformation(String username);

    void updateInformation(String username, UserUpdateRequest request);

    void updateAvatar(String username, MultipartFile avatar);

    InstructorResponse getInstructorInformation(int id);

    UserResponse getOtherUserInformation(int id);

}

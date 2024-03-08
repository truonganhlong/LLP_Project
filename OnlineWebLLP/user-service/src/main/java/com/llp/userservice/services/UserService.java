package com.llp.userservice.services;

import com.llp.userservice.dtos.user.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse loginViaForm(AuthenticationRequest request);
    AuthenticationResponse registerAsAdmin(RegisterRequest request);

    UserResponse getUserInformation(String username);

    void updateInformation(String username, UserUpdateRequest request);

    void updateAvatar(String username, MultipartFile avatar);

    InstructorResponse getInstructorInformation(int id);

    UserResponse getOtherUserInformation(int id);

    List<InstructorResponse> getPopularInstructorByCategory(int categoryId);

    List<InstructorResponse> getPopularInstructorBySubCategory(int subCategoryId);

    void forgetPassword(String email);
    void changePassword(int userId, String oldPass, String newPass, String reNewPass);
    AuthenticationResponse loginViaGoogle(String tokenId);

}

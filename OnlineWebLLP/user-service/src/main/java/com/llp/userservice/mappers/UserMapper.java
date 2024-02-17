package com.llp.userservice.mappers;

import com.llp.userservice.dtos.user.InstructorResponse;
import com.llp.userservice.dtos.user.UserResponse;
import com.llp.userservice.entities.User;

public class UserMapper {
    public static UserResponse convertToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .headline(user.getHeadline())
                .biography(user.getBiography())
                .twitterLink(user.getTwitterLink())
                .facebookLink(user.getFacebookLink())
                .linkedinLink(user.getLinkedinLink())
                .youtubeLink(user.getYoutubeLink())
                .imageLink(user.getImageLink())
                .build();
    }

    public static InstructorResponse convertToInstructorResponse(User user){
        return InstructorResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .headline(user.getHeadline())
                .imageLink(user.getImageLink())
                .biography(user.getBiography())
                .build();
    }

}

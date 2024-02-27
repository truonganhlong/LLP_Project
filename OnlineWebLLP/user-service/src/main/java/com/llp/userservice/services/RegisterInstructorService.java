package com.llp.userservice.services;

import com.llp.userservice.dtos.registerInstructor.FormRequest;
import com.llp.userservice.dtos.registerInstructor.QuestionResponse;
import com.llp.userservice.dtos.user.AuthenticationResponse;

import java.util.List;

public interface RegisterInstructorService {
    List<QuestionResponse> getAllQuestion();

    AuthenticationResponse fillForm(int userId, List<FormRequest> requests);
}

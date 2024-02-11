package com.llp.userservice.services;

import com.llp.userservice.dtos.registerInstructor.FormRequest;
import com.llp.userservice.dtos.registerInstructor.QuestionResponse;

import java.util.List;

public interface RegisterInstructorService {
    List<QuestionResponse> getAllQuestion();

    void fillForm(int userId, List<FormRequest> requests);
}

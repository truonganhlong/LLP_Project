package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.dtos.registerInstructor.AnswerResponse;
import com.llp.userservice.dtos.registerInstructor.FormRequest;
import com.llp.userservice.dtos.registerInstructor.QuestionResponse;
import com.llp.userservice.entities.Answer;
import com.llp.userservice.entities.Question;
import com.llp.userservice.entities.RegisterInstructorForm;
import com.llp.userservice.mappers.RegisterInstructorMapper;
import com.llp.userservice.repositories.*;
import com.llp.userservice.services.RegisterInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterInstructorServiceImpl implements RegisterInstructorService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final RegisterInstructorFormRepository registerInstructorFormRepository;
    private final UserRoleRepository userRoleRepository;
    @Override
    public List<QuestionResponse> getAllQuestion() {
        try {
            List<Question> questions = questionRepository.findAll();
            List<QuestionResponse> data = questions.stream().map(RegisterInstructorMapper :: convertToQuestionResponse).collect(Collectors.toList());
            for (var question:data) {
                List<Answer> answers = answerRepository.getAllByQuestionId(question.getId().intValue());
                question.setAnswers(answers.stream().map(RegisterInstructorMapper :: convertToAnswerResponse).collect(Collectors.toList()));
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void fillForm(int userId, List<FormRequest> requests) {
        try {
            for (var request:requests) {
                registerInstructorFormRepository.create(userId, request.getQuestionId(), request.getAnswerId());
            }
            userRoleRepository.create(userId, 2);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

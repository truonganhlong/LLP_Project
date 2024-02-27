package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.dtos.registerInstructor.AnswerResponse;
import com.llp.userservice.dtos.registerInstructor.FormRequest;
import com.llp.userservice.dtos.registerInstructor.QuestionResponse;
import com.llp.userservice.dtos.user.AuthenticationResponse;
import com.llp.userservice.entities.Answer;
import com.llp.userservice.entities.Question;
import com.llp.userservice.entities.RegisterInstructorForm;
import com.llp.userservice.entities.UserRole;
import com.llp.userservice.entities.keys.UserRoleKey;
import com.llp.userservice.mappers.RegisterInstructorMapper;
import com.llp.userservice.repositories.*;
import com.llp.userservice.services.JwtService;
import com.llp.userservice.services.RegisterInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterInstructorServiceImpl implements RegisterInstructorService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final RegisterInstructorFormRepository registerInstructorFormRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtService jwtService;
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
    public AuthenticationResponse fillForm(int userId, List<FormRequest> requests) {
        try {
            for (var request:requests) {
                registerInstructorFormRepository.create(userId, request.getQuestionId(), request.getAnswerId());
            }
            userRoleRepository.create(userId, 2);
            var user = userRepository.findById((long) userId)
                    .orElseThrow();
            List<UserRole> userRoleList = new ArrayList<>();
            userRoleList.add(userRoleRepository.getById(new UserRoleKey((long) userId, 1L)));
            userRoleList.add(userRoleRepository.getById(new UserRoleKey((long) userId, 2L)));
            user.setUserRoles(userRoleList);
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

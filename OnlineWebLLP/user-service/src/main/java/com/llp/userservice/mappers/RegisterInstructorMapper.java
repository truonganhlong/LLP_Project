package com.llp.userservice.mappers;

import com.llp.userservice.dtos.registerInstructor.AnswerResponse;
import com.llp.userservice.dtos.registerInstructor.QuestionResponse;
import com.llp.userservice.entities.Answer;
import com.llp.userservice.entities.Question;

public class RegisterInstructorMapper {
    public static QuestionResponse convertToQuestionResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .description(question.getDescription())
                .question(question.getQuestion())
                .build();
    }

    public static AnswerResponse convertToAnswerResponse(Answer answer){
        return AnswerResponse.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .build();
    }
}

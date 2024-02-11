package com.llp.userservice.repositories;

import com.llp.userservice.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> getAllByQuestionId(int questionId);
    Answer getById(int id);
}

package com.common.jmark.domain.repository.interviewQuestion;

import com.common.jmark.domain.entity.InterviewQuestion.Answer;
import com.common.jmark.domain.entity.InterviewQuestion.Question;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestionAndUser(Question question, User user);
}

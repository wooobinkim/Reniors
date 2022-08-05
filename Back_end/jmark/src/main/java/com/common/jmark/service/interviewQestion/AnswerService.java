package com.common.jmark.service.interviewQestion;

import com.common.jmark.common.exception.NotAuthException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.InterviewQuestion.Answer;
import com.common.jmark.domain.entity.InterviewQuestion.Question;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.interviewQuestion.AnswerRepository;
import com.common.jmark.domain.repository.interviewQuestion.QuestionRepository;
import com.common.jmark.dto.interviewQuestion.AnswerCreateRequest;
import com.common.jmark.dto.interviewQuestion.AnswerResponse;
import com.common.jmark.dto.interviewQuestion.AnswerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.common.jmark.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.jmark.common.exception.NotFoundException.ANSWER_NOT_FOUND;
import static com.common.jmark.common.exception.NotFoundException.QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public Long create(Long questionId, AnswerCreateRequest request, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        if(answerRepository.findByQuestionAndUser(question, user).isPresent()){
            Answer answer = answerRepository.findByQuestionAndUser(question, user)
                    .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
            answer.update(request.getAnswer());
            return answer.getId();
        }
        else{
            Answer newAnswer = Answer.create(request.getAnswer(), question, user);
            return answerRepository.save(newAnswer).getId();
        }
    }

    @Transactional
    public void update(Long questionId, AnswerUpdateRequest request, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        Answer answer = answerRepository.findByQuestionAndUser(question, user)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
        answer.update(request.getAnswer());
    }

    @Transactional
    public void delete(Long answerId, User user) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
        if(answer.getUser() == user) {
            answerRepository.delete(answer);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public AnswerResponse getAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
        return AnswerResponse.response(answer);
    }
}

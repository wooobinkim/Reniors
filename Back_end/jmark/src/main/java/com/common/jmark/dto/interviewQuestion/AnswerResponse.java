package com.common.jmark.dto.interviewQuestion;

import com.common.jmark.domain.entity.InterviewQuestion.Answer;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.dto.category.GugunResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String answer;

    public static AnswerResponse response(Answer answer){
        return new AnswerResponse(answer.getId(), answer.getAnswer());
    }
}

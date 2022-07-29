package com.common.jmark.dto;

import com.common.jmark.domain.entity.Eval;
import com.common.jmark.domain.entity.EvalQuestion;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class EvalQuestionDto {

    private Long id;
    private String contents;
    private EvalDto evalDto;

    public EvalQuestionDto(EvalQuestion evalQuestion, EvalDto evalDto) {
        this.id = evalQuestion.getId();
        this.contents = evalQuestion.getContents();
        this.evalDto = evalDto;
    }
}

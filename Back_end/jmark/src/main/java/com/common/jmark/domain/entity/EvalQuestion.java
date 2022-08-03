package com.common.jmark.domain.entity;

import com.common.jmark.dto.EvalQuestionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvalQuestion {
    @Id
    @GeneratedValue
    @Column(name = "eval_question_id")
    private Long id;

    @NotNull
    @Column(length = 500)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eval_id")
    Eval eval;


    public EvalQuestion(EvalQuestionDto evalQuestionDto, Eval eval) {
        this.id = evalQuestionDto.getId();
        this.contents = evalQuestionDto.getContents();
        this.eval = eval;
    }

    public void update(EvalQuestionDto evalQuestionDto, Eval eval) {
        this.contents = evalQuestionDto.getContents();
        this.eval = eval;
    }
}

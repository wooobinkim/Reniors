package com.common.jmark.dto.eval;

import com.common.jmark.domain.entity.EvalQuestion;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class EvalQuestionResponse {

    private Long id;
    private String contents;;

    public static EvalQuestionResponse response(EvalQuestion evalQuestion){
        return new EvalQuestionResponse(
                evalQuestion.getId(),
                evalQuestion.getContents()
        );
    }
}

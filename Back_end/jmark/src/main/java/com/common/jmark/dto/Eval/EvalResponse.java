package com.common.jmark.dto.Eval;

import com.common.jmark.domain.entity.Eval;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class EvalResponse {
    private Long id;
    private String name;

    public static EvalResponse response(Eval eval){
        return new EvalResponse(
                eval.getId(),
                eval.getName()
        );
    }
}

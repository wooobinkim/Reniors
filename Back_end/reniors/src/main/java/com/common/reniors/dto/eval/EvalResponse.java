package com.common.reniors.dto.eval;

import com.common.reniors.domain.entity.Eval;
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

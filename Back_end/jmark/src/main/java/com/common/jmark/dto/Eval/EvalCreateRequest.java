package com.common.jmark.dto.Eval;

import com.common.jmark.domain.entity.Eval;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class EvalCreateRequest {
    private String name;
    private Long jobOpeningId;
}

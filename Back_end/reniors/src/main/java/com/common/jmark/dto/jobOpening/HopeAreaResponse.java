package com.common.jmark.dto.jobOpening;

import com.common.jmark.domain.entity.HopeArea;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class HopeAreaResponse {
    private Long id;
    private String gugun;

    public static HopeAreaResponse response(HopeArea hopeArea){
        return new HopeAreaResponse(
                hopeArea.getId(),
                hopeArea.getGugun().getName()
        );
    }
}

package com.common.jmark.dto;

import com.common.jmark.domain.entity.HopeArea;
import com.common.jmark.dto.category.GugunResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class HopeAreaDto {
    private Long id;

    private Long gugunId;
    private GugunResponse gugunResponse;

    public HopeAreaDto(HopeArea hopeArea, GugunResponse gugunResponse) {
        this.id= hopeArea.getId();
        this.gugunResponse = gugunResponse;
    }
}

package com.common.jmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class JobOpeningSearchDto {

    private String contents;
    private int salary;
    private Long jobChildCategoryId;
    private Long guGunCode;
}

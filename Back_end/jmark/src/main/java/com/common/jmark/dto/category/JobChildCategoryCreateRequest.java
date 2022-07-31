package com.common.jmark.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobChildCategoryCreateRequest {

    @NotNull(message = "소분류 카테고리 이름은 필수입니다.")
    private String name;
}
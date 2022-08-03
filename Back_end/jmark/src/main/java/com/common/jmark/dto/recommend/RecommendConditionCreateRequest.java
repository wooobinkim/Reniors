package com.common.jmark.dto.recommend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendConditionCreateRequest {
    private Long jobChildCategoryId;

    private Long gugunId;

    private int workingDay;

    private int minSalary;
}

package com.common.jmark.dto.recommend;

import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendConditionUpdateRequest {
    private Long jobChildCategoryId;

    private Long gugunId;

    private int workingDay;

    private int minSalary;
}
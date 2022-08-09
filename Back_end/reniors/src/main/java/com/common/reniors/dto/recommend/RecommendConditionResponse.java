package com.common.reniors.dto.recommend;

import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.dto.category.GugunResponse;
import com.common.reniors.dto.category.JobChildCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendConditionResponse {
    private Long id;

    private JobChildCategoryResponse jobChildCategoryResponse;

    private GugunResponse gugunResponse;

    private int workingDay;

    private int minSalary;

    public static RecommendConditionResponse response(RecommendCondition recommendCondition) {
        return new RecommendConditionResponse(recommendCondition.getId(),
                JobChildCategoryResponse.response(recommendCondition.getJobChildCategory()),
                GugunResponse.response(recommendCondition.getGugun()),
                recommendCondition.getWorkingDay(),
                recommendCondition.getMinSalary());
    }
}
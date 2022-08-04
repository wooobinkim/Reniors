package com.common.jmark.dto.recommend;

import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendConditionResponse {
    private Long id;

    private UserResponse userResponse;

    private JobChildCategoryResponse jobChildCategoryResponse;

    private GugunResponse gugunResponse;

    private int workingDay;

    private int minSalary;

    public static RecommendConditionResponse response(RecommendCondition recommendCondition) {
        return new RecommendConditionResponse(recommendCondition.getId(),
                UserResponse.response(recommendCondition.getUser()),
                JobChildCategoryResponse.response(recommendCondition.getJobChildCategory()),
                GugunResponse.response(recommendCondition.getGugun()),
                recommendCondition.getWorkingDay(),
                recommendCondition.getMinSalary());
    }
}
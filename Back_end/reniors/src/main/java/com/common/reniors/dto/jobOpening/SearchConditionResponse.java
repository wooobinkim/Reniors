package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.Type.TypeEmployment;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.SearchCondition;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class SearchConditionResponse {
    private Long id;
    private String name;
    private int minCareer;
    private int minSalary;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;
    private Long jobParentCategoryId;
    private String jobParentCategoryName;

    public static SearchConditionResponse response(SearchCondition searchCondition, String jobParentCategoryName){
        return new SearchConditionResponse(
                searchCondition.getId(),
                searchCondition.getName(),
                searchCondition.getMinCareer(),
                searchCondition.getMinSalary(),
                searchCondition.getWorkingDay(),
                searchCondition.getTypeEmployment(),
                searchCondition.getLastEdu(),
                searchCondition.getJobParentCategoryId(),
                jobParentCategoryName
        );
    }
}

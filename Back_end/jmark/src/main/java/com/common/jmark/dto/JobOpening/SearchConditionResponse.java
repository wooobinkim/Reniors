package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.SearchCondition;
import com.common.jmark.dto.category.JobChildCategoryResponse;
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
    private String jobChildCategory;

    public static SearchConditionResponse response(SearchCondition searchCondition){
        return new SearchConditionResponse(
                searchCondition.getId(),
                searchCondition.getName(),
                searchCondition.getMinCareer(),
                searchCondition.getMinSalary(),
                searchCondition.getWorkingDay(),
                searchCondition.getTypeEmployment(),
                searchCondition.getLastEdu(),
                searchCondition.getJobChildCategory().getName()
        );
    }
}

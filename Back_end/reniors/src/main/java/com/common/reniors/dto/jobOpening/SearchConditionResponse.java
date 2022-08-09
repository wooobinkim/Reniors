package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.Type.typeEmployment;
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
    private typeEmployment typeEmployment;
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

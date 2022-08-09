package com.common.reniors.dto.jobOpening;

import com.common.reniors.service.eval.Type.Employment;
import com.common.reniors.service.eval.Type.LastEdu;
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
    private Employment employment;
    private LastEdu lastEdu;
    private String jobChildCategory;

    public static SearchConditionResponse response(SearchCondition searchCondition){
        return new SearchConditionResponse(
                searchCondition.getId(),
                searchCondition.getName(),
                searchCondition.getMinCareer(),
                searchCondition.getMinSalary(),
                searchCondition.getWorkingDay(),
                searchCondition.getEmployment(),
                searchCondition.getLastEdu(),
                searchCondition.getJobChildCategory().getName()
        );
    }
}

package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Type.Employment;
import com.common.jmark.domain.entity.Type.LastEdu;
import com.common.jmark.domain.entity.SearchCondition;
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

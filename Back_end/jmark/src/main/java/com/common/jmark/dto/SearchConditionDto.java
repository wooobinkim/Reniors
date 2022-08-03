package com.common.jmark.dto;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.SearchCondition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class SearchConditionDto {
    private Long id;
    private String name;
    private int minCareer;
    private int minSalary;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;

    public SearchConditionDto(SearchCondition searchCondition) {
        this.id = searchCondition.getId();
        this.name = searchCondition.getName();
        this.minCareer = searchCondition.getMinCareer();
        this.minSalary = searchCondition.getMinSalary();
        this.workingDay = searchCondition.getWorkingDay();
        this.typeEmployment = searchCondition.getTypeEmployment();
        this.lastEdu = searchCondition.getLastEdu();
    }
}

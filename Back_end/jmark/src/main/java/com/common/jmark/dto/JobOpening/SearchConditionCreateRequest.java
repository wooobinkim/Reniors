package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class SearchConditionCreateRequest {
    private String name;
    private int minCareer;
    private int minSalary;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;
    private Long jobChildCategoryId;
}

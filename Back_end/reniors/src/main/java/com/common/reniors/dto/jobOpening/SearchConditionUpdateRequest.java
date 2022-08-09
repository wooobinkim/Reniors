package com.common.reniors.dto.jobOpening;

import com.common.reniors.service.eval.Type.Employment;
import com.common.reniors.service.eval.Type.LastEdu;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class SearchConditionUpdateRequest {
    private String name;
    private int minCareer;
    private int minSalary;
    private int workingDay;
    private Employment employment;
    private LastEdu lastEdu;
    private Long jobChildCategoryId;
}

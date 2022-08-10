package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.Type.IsFinish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningCompanyResponse {

    private Long id;
    private String title;
    private String jobChildCategoryName;
    private int applies;
    private IsFinish isFinish;

    public static JobOpeningCompanyResponse response(JobOpening jobOpening){
        return new JobOpeningCompanyResponse(
                jobOpening.getId(),
                jobOpening.getTitle(),
                jobOpening.getJobChildCategory().getName(),
                jobOpening.getApplies().size(),
                jobOpening.getIsFinish()
        );
    }
}

package com.common.jmark.dto.jobOpening;

import com.common.jmark.domain.entity.JobOpening;
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

    public static JobOpeningCompanyResponse response(JobOpening jobOpening){
        return new JobOpeningCompanyResponse(
                jobOpening.getId(),
                jobOpening.getTitle(),
                jobOpening.getJobChildCategory().getName(),
                jobOpening.getApplies().size()
        );
    }
}

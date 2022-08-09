package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.service.eval.Type.IsFinish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningResponse {

    private Long id;
    private Date createdDate;
    private Date finishedDate;
    private String title;
    private IsFinish isFinish;
    private String companyName;


    public static JobOpeningResponse response(JobOpening jobOpening){
        return new JobOpeningResponse(
                jobOpening.getId(),
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getTitle(),
                jobOpening.getIsFinish(),
                jobOpening.getCompany().getName()
        );
    }
}

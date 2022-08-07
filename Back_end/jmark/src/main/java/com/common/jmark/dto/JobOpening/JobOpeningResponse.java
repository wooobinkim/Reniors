package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.Type.IsFinish;
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
    private String companyImgName;
    private String companyImgPath;


    public static JobOpeningResponse response(JobOpening jobOpening){
        return new JobOpeningResponse(
                jobOpening.getId(),
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getTitle(),
                jobOpening.getIsFinish(),
                jobOpening.getCompany().getName(),
                jobOpening.getCompany().getCompanyImgName(),
                jobOpening.getCompany().getCompanyImgPath()
        );
    }
}

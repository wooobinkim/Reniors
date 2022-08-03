package com.common.jmark.dto;

import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.dto.user.UserResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class ApplyDto {
    private Long id;
    private JobOpeningProcess jobOpeningProcess;
    private Date interviewDate;
    private JobOpeningDto jobOpeningDto;

    private UserResponse userResponse;

    public ApplyDto(JobOpeningProcess jobOpeningProcess) {
        this.jobOpeningProcess = jobOpeningProcess;
    }

    public ApplyDto(Apply apply, JobOpeningDto jobOpeningDto, UserResponse userResponse) {
        this.id = apply.getId();
        this.jobOpeningProcess = apply.getJobOpeningProcess();
        this.interviewDate = apply.getInterviewDate();
        this.jobOpeningDto = jobOpeningDto;
        this.userResponse = userResponse;
    }
}

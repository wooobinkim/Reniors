package com.common.jmark.dto.Apply;

import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import lombok.*;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResponse {
    private Long id;
    private JobOpeningProcess jobOpeningProcess;
    private Date interviewDate;
    private Long jobOpeningId;

    public static ApplyResponse response(Apply apply){
        return new ApplyResponse(
                apply.getId(),
                apply.getJobOpeningProcess(),
                apply.getInterviewDate(),
                apply.getJobOpening().getId()
        );
    }
}

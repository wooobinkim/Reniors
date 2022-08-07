package com.common.jmark.dto.Apply;

import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.domain.entity.Type.Gender;
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
    private Long userId;
    private Gender gender;
    private Date birth;
    private String phone;


    public static ApplyResponse response(Apply apply){
        return new ApplyResponse(
                apply.getId(),
                apply.getJobOpeningProcess(),
                apply.getInterviewDate(),
                apply.getJobOpening().getId(),
                apply.getUser().getId(),
                apply.getUser().getGender(),
                apply.getUser().getBirth(),
                apply.getUser().getPhone()
        );
    }
}

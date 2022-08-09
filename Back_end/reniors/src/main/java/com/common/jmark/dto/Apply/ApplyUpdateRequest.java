package com.common.jmark.dto.Apply;

import com.common.jmark.domain.entity.Type.JobOpeningProcess;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class ApplyUpdateRequest {
    private JobOpeningProcess jobOpeningProcess;
    private Date interviewDate;

}

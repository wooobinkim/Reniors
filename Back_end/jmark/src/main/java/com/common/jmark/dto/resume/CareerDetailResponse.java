package com.common.jmark.dto.resume;

import com.common.jmark.domain.entity.resume.CareerDetail;
import com.common.jmark.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerDetailResponse {
    private Long id;

    private String companyName;

    private Date startedAt;

    private Date finishedAt;

    private String jobContents;

    private User user;

    public static CareerDetailResponse response(CareerDetail careerDetail) {
        return new CareerDetailResponse(careerDetail.getId(), careerDetail.getCompanyName(), careerDetail.getStartedAt(), careerDetail.getFinishedAt(), careerDetail.getJobContents(), careerDetail.getUser());
    }
}

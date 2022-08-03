package com.common.jmark.dto.resume;

import com.common.jmark.domain.entity.resume.License;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseResponse {
    private Long id;

    private String name;

    private Date passedAt;

    private String grade;

    private UserResponse userResponse;

    public static LicenseResponse response(License license) {
        return new LicenseResponse(license.getId(),
                license.getName(),
                license.getPassedAt(),
                license.getGrade(),
                UserResponse.response(license.getUser()));
    }
}

package com.common.jmark.dto.user;

import com.common.jmark.domain.entity.Type.Gender;
import com.common.jmark.domain.entity.Type.IsOpen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String userAppId;

    private String userAppPwd;

    private String kakaoId;

    @NotBlank
    private String name;

    @NotBlank
    private Date birth;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private String phone;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    @NotBlank
    private String address;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private IsOpen isOpen;

    private int workingDay;

    private long minSalary;

    private String portfolioName;

    private String portfolioPath;
}

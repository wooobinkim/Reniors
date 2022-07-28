package com.common.jmark.dto.user;

import com.common.jmark.domain.entity.Enum.TypeGender;
import com.common.jmark.domain.entity.Enum.TypeIsOpen;
import com.common.jmark.domain.entity.user.User;
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
public class UserResponse {
    private String userAppId;

    private String userAppPwd;

    private String kakaoId;

    private String name;

    private Date birth;

    private TypeGender gender;

    private String phone;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    private String address;

    private TypeIsOpen isOpen;

    private int workingDay;

    private long minSalary;

    private String portfolioName;

    private String portfolioPath;

    public static UserResponse response(User user){
        return new UserResponse(user.getUserAppId(),user.getUserAppPwd(), user.getKakaoId(), user.getName(),user.getBirth(), user.getGender(),user.getPhone(), user.getTotalCareer(), user.getProfileImgName(), user.getProfileImgPath(), user.getAddress(), user.getIsOpen(), user.getWorkingDay(), user.getMinSalary(), user.getPortfolioName(),user.getPortfolioPath());
    }
}

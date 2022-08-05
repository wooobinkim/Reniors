package com.common.jmark.dto.user;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Type.Gender;
import com.common.jmark.domain.entity.Type.IsOpen;
import com.common.jmark.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String userAppId;

    private String userAppPwd;

    private String kakaoId;

    private String name;

    private Date birth;

    private Gender gender;

    private String phone;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    private String address;

    private IsOpen isOpen;

    private LastEdu lastEdu;

    private String portfolioName;

    private String portfolioPath;

    public static UserResponse response(User user){
        return new UserResponse(user.getId(), user.getUserAppId(), user.getUserAppPwd(), user.getKakaoId(), user.getName(),user.getBirth(), user.getGender(),user.getPhone(), user.getTotalCareer(), user.getProfileImgName(), user.getProfileImgPath(), user.getAddress(), user.getIsOpen(), user.getLastEdu(), user.getPortfolioName(),user.getPortfolioPath());
    }
}

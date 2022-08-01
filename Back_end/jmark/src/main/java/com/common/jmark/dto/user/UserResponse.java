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

    private String name;

    private Date birth;

    private Gender gender;

    private String phone;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    private String address;

    private IsOpen isOpen;

    private int workingDay;

    private LastEdu lastEdu;

    private long minSalary;

    private String portfolioName;

    private String portfolioPath;

    public static UserResponse response(User user){
        return new UserResponse(user.getId(), user.getName(),user.getBirth(), user.getGender(),user.getPhone(), user.getTotalCareer(), user.getProfileImgName(), user.getProfileImgPath(), user.getAddress(), user.getIsOpen(), user.getWorkingDay(), user.getLastEdu(), user.getMinSalary(), user.getPortfolioName(),user.getPortfolioPath());
    }
}

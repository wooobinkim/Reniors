package com.common.reniors.dto.kakao;

import com.common.reniors.domain.entity.Type.Gender;
import com.common.reniors.domain.entity.Type.IsOpen;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserResponse {
    private Long id;

    private String kakaoId;

    private String name;

    private Date birth;

    private Gender gender;

    private String phone;

    private int totalCareer;

    private String address;

    private String extraAddress;

    private IsOpen isOpen;

    private LastEdu lastEdu;

    private String baseURL;

    private String userProfile;

    public static KakaoUserResponse response(User user){
        return new KakaoUserResponse(user.getId(), user.getKakaoId(), user.getName(), user.getBirth(), user.getGender(), user.getPhone(), user.getTotalCareer(), user.getAddress(), user.getExtraAddress(), user.getIsOpen(), user.getLastEdu(), user.getBaseURL(), user.getUserProfile());
    }
}

package com.common.reniors.dto.kakao;

import com.common.reniors.domain.entity.Type.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfo {
    private Long id;
    private String nickname;
    private String email;
    private String birthday;
    private Gender gender;
}

package com.common.jmark.dto.resume;

import com.common.jmark.domain.entity.resume.Award;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardResponse {
    private Long id;

    private String name;

    private Date awardedAt;

    private UserResponse userResponse;

    public static AwardResponse response(Award award) {
        return new AwardResponse(award.getId(),
                award.getName(),
                award.getAwardedAt(),
                UserResponse.response(award.getUser()));
    }
}

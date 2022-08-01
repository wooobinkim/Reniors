package com.common.jmark.dto.resume;

import com.common.jmark.domain.entity.resume.Award;
import com.common.jmark.domain.entity.user.User;
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

    private User user;

    public static AwardResponse response(Award award) {
        return new AwardResponse(award.getId(),
                award.getName(),
                award.getAwardedAt(),
                award.getUser());
    }
}

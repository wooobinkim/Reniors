package com.common.reniors.dto.user;

import com.common.reniors.service.eval.Type.LastEdu;
import com.common.reniors.service.eval.Type.Gender;
import com.common.reniors.service.eval.Type.IsOpen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    private String name;

    @NotNull
    private Date birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private String phone;

    private int totalCareer;

    @NotBlank
    private String address;

    private String extraAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IsOpen isOpen;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    @NotNull
    private boolean changeProfile;
}

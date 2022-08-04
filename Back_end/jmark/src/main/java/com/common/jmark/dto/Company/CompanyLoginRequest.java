package com.common.jmark.dto.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyLoginRequest {

    @NotBlank
    private String companyAppId;

    @NotBlank
    private String companyAppPwd;
}

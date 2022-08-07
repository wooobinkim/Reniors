package com.common.jmark.dto.Company;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.TypeCompany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class CompanyCreateRequest {
    private String name;
    private String companyAppId;
    private String companyAppPwd;
    private String establishedAt;
    private String companyUrl;
    private String address;
    private String companyImgName;
    private String companyImgPath;
    private String companyNum;
    private String companyPhone;
    private String representative;
    private String representativePhone;
    private TypeCompany typeCompany;

}

package com.common.jmark.dto;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.dto.category.GugunResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class CompanyDto {
    private Long id;
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

    private String representativePhone;
    private TypeCompany typeCompany;

    public CompanyDto(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.companyAppId = company.getCompanyAppId();
        this.companyAppPwd = company.getCompanyAppPwd();
        this.establishedAt = company.getEstablishedAt();
        this.companyUrl = company.getCompanyUrl();
        this.address = company.getAddress();
        this.companyImgName = company.getCompanyImgName();
        this.companyImgPath = company.getCompanyImgPath();
        this.companyNum = company.getCompanyNum();
        this.companyPhone = company.getCompanyPhone();
        this.representativePhone = company.getRepresentativePhone();
        this.typeCompany = company.getTypeCompany();
    }

}

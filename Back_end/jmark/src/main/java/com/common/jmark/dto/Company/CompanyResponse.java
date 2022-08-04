package com.common.jmark.dto.Company;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.TypeCompany;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
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

    public static CompanyResponse response(Company company){
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCompanyAppId(),
                company.getCompanyAppPwd(),
                company.getEstablishedAt(),
                company.getCompanyUrl(),
                company.getAddress(),
                company.getCompanyImgName(),
                company.getCompanyImgPath(),
                company.getCompanyNum(),
                company.getCompanyPhone(),
                company.getRepresentativePhone(),
                company.getTypeCompany()
        );
    }


}

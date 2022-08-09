package com.common.reniors.dto.company;

import com.common.reniors.service.eval.Type.Company;
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
    private String extraAddress;
    private String companyNum;
    private String companyPhone;
    private String representative;
    private String representativePhone;
    private Company company;

    public static CompanyResponse response(com.common.reniors.domain.entity.Company company){
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCompanyAppId(),
                company.getCompanyAppPwd(),
                company.getEstablishedAt(),
                company.getCompanyUrl(),
                company.getAddress(),
                company.getExtraAddress(),
                company.getCompanyNum(),
                company.getCompanyPhone(),
                company.getRepresentative(),
                company.getRepresentativePhone(),
                company.getCompany()
        );
    }


}

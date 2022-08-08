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
    private String extraAddress;
    private String companyNum;
    private String companyPhone;
    private String representative;
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
                company.getExtraAddress(),
                company.getCompanyNum(),
                company.getCompanyPhone(),
                company.getRepresentative(),
                company.getRepresentativePhone(),
                company.getTypeCompany()
        );
    }


}

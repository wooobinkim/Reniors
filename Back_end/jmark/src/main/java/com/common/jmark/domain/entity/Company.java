package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.dto.Company.CompanyCreateRequest;
import com.common.jmark.dto.Company.CompanyUpdateRequest;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(length = 100)
    private String companyAppId;

    @NotNull
    @Column(length = 200)
    private String companyAppPwd;

    @NotNull
    @Column(length = 50)
    private String establishedAt;

    @Column(length = 100)
    private String companyUrl;

    @NotNull
    @Column(length = 100)
    private String address;

    @NotNull
    @Column(length = 50)
    private String companyImgName;

    @NotNull
    @Column(length = 100)
    private String companyImgPath;

    @NotNull
    @Column(length = 100)
    private String companyNum;

    @NotNull
    @Column(length = 30)
    private String companyPhone;

    @NotNull
    @Column(length = 30)
    private String representativePhone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeCompany typeCompany;

    //회사 - 공고 연관관계
    @OneToMany(mappedBy = "company")
    private List<JobOpening> jobOpenings = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Eval> evals = new ArrayList<>();

    public static Company create(String name, String companyAppId, String companyAppPwd, String establishedAt, String companyUrl, String address, String companyImgName, String companyImgPath, String companyNum, String companyPhone, String representativePhone, TypeCompany typeCompany) {
        Company company = new Company();
        company.name = name;
        company.companyAppId = companyAppId;
        company.companyAppPwd = companyAppPwd;
        company.establishedAt = establishedAt;
        company.companyUrl = companyUrl;
        company.address = address;
        company.companyImgName = companyImgName;
        company.companyImgPath = companyImgPath;
        company.companyNum = companyNum;
        company.companyPhone = companyPhone;
        company.representativePhone = representativePhone;
        company.typeCompany = typeCompany;
        return company;
    }

    public void update(CompanyUpdateRequest companyUpdateRequest){
        this.name = companyUpdateRequest.getName();
        this.companyAppId = companyUpdateRequest.getCompanyAppId();
        this.companyAppPwd = companyUpdateRequest.getCompanyAppPwd();
        this.establishedAt = companyUpdateRequest.getEstablishedAt();
        this.companyUrl = companyUpdateRequest.getCompanyUrl();
        this.address = companyUpdateRequest.getAddress();
        this.companyImgName = companyUpdateRequest.getCompanyImgName();
        this.companyImgPath = companyUpdateRequest.getCompanyImgPath();
        this.companyNum = companyUpdateRequest.getCompanyNum();
        this.companyPhone = companyUpdateRequest.getCompanyPhone();
        this.representativePhone = companyUpdateRequest.getRepresentativePhone();
        this.typeCompany = companyUpdateRequest.getTypeCompany();
    }


}

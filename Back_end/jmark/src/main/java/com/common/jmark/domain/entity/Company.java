package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.dto.CompanyDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String companyAppId;
    @NotNull
    private String companyAppPwd;
    @NotNull
    private String establishedAt;

    private String companyUrl;
    @NotNull
    private String address;
    @NotNull
    private String companyImgName;
    @NotNull
    private String companyImgPath;
    @NotNull
    private String companyNum;
    @NotNull
    private String companyPhone;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeCompany typeCompany;

    //회사 - 기업형태 연관관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_company_id")
//    private TypeCompany typeCompany;

    //회사 - 공고 연관관계
    @OneToMany(mappedBy = "company")
    private List<JobOpening> jobOpenings = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Eval> evals = new ArrayList<>();

    @Builder
    public Company(CompanyDto companyDto) {
        this.name = companyDto.getName();
        this.companyAppId = companyDto.getCompanyAppId();
        this.companyAppPwd = companyDto.getCompanyAppPwd();
        this.establishedAt = companyDto.getEstablishedAt();
        this.companyUrl = companyDto.getCompanyUrl();
        this.address = companyDto.getAddress();
        this.companyImgName = companyDto.getCompanyImgName();
        this.companyImgPath = companyDto.getCompanyImgPath();
        this.companyNum = companyDto.getCompanyNum();
        this.companyPhone = companyDto.getCompanyPhone();
        this.typeCompany = companyDto.getTypeCompany();
    }

    public void update(CompanyDto companyDto){
        this.name = companyDto.getName();
        this.establishedAt = companyDto.getEstablishedAt();
        this.companyUrl = companyDto.getCompanyUrl();
        this.address = companyDto.getAddress();
        this.companyImgName = companyDto.getCompanyImgName();
        this.companyImgPath = companyDto.getCompanyImgPath();
        this.companyNum = companyDto.getCompanyNum();
        this.companyPhone = companyDto.getCompanyPhone();
        this.typeCompany = companyDto.getTypeCompany();
    }
}

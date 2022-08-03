package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.dto.CompanyDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    @Column(length = 50)
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
        this.representativePhone = companyDto.getRepresentativePhone();
        this.typeCompany = companyDto.getTypeCompany();
    }

    public void update(CompanyDto companyDto){
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
        this.representativePhone = companyDto.getRepresentativePhone();
        this.typeCompany = companyDto.getTypeCompany();
    }


}

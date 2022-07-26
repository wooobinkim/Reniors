package domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id @GeneratedValue
    @Column(name = "company_id")
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

    //회사 - 기업형태 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private TypeCompany typeCompany;

    //회사 - 공고 연관관계
    @OneToMany(mappedBy = "company")
    private List<JobOpening> jobOpenings = new ArrayList<>();

    public Company(String name, String companyAppId, String companyAppPwd, String establishedAt, String companyUrl, String address, String companyImgName, String companyImgPath, String companyNum, String companyPhone, TypeCompany typeCompany) {
        this.name = name;
        this.companyAppId = companyAppId;
        this.companyAppPwd = companyAppPwd;
        this.establishedAt = establishedAt;
        this.companyUrl = companyUrl;
        this.address = address;
        this.companyImgName = companyImgName;
        this.companyImgPath = companyImgPath;
        this.companyNum = companyNum;
        this.companyPhone = companyPhone;
        this.typeCompany = typeCompany;
    }
}

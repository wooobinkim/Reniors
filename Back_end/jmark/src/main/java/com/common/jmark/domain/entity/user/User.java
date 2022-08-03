package com.common.jmark.domain.entity.user;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.SearchCondition;
import com.common.jmark.domain.entity.Type.Gender;
import com.common.jmark.domain.entity.Type.IsOpen;
import com.common.jmark.domain.entity.Type.Role;
import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.resume.Award;
import com.common.jmark.domain.entity.resume.CareerDetail;
import com.common.jmark.domain.entity.resume.License;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotNull
    @Column(length = 100)
    private String userAppId;

    @NotNull
    @Column(length = 50)
    private String userAppPwd;

    @Column(length = 100)
    private String kakaoId;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private Date birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(length = 30)
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    private int totalCareer;

    @NotNull
    @Column(length = 50)
    private String profileImgName;

    @NotNull
    @Column(length = 100)
    private String profileImgPath;

    @NotNull
    @Column(length = 100)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IsOpen isOpen;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    @Column(length = 100)
    private String portfolioName;

    @Column(length = 100)
    private String portfolioPath;

    // 회원 - 경력사항 연관관계
    @OneToMany(mappedBy = "user")
    private List<CareerDetail> careerDetails = new ArrayList<>();

    // 회원 - 수상경력 연관관계
    @OneToMany(mappedBy = "user")
    private List<Award> awards = new ArrayList<>();

    // 회원 - 자격증 연관관계
    @OneToMany(mappedBy = "user")
    private List<License> licenses = new ArrayList<>();

    // 회원 - 추천 조건 연관관계
    @OneToOne(mappedBy = "user")
    private RecommendCondition recommendCondition;

    // 회원 - 공고 지원 연관관계
    @OneToMany(mappedBy = "user")
    List<Apply> applies = new ArrayList<>();

    // 회원 - 검색 조건 연관관계
    @OneToMany(mappedBy = "user")
    List<SearchCondition> searchConditions = new ArrayList<>();

   public static User create(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, Gender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, IsOpen isOpen, LastEdu lastEdu, String portfolioName, String portfolioPath) {
        User user = new User();
        user.userAppId = userAppId;
        user.userAppPwd = userAppPwd;
        user.kakaoId = kakaoId;
        user.name = name;
        user.birth = birth;
        user.gender = gender;
        user.phone = phone;
        user.role = Role.ROLE_USER;
        user.totalCareer = totalCareer;
        user.profileImgName = profileImgName;
        user.profileImgPath = profileImgPath;
        user.address = address;
        user.isOpen = isOpen;
        user.lastEdu = lastEdu;
        user.portfolioName = portfolioName;
        user.portfolioPath = portfolioPath;
        return user;
    }

    public void update(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, Gender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, IsOpen isOpen, LastEdu lastEdu, String portfolioName, String portfolioPath) {
        this.userAppId = userAppId;
        this.userAppPwd = userAppPwd;
        this.kakaoId = kakaoId;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.totalCareer = totalCareer;
        this.profileImgName = profileImgName;
        this.profileImgPath = profileImgPath;
        this.address = address;
        this.isOpen = isOpen;
        this.lastEdu = lastEdu;
        this.portfolioName = portfolioName;
        this.portfolioPath = portfolioPath;
    }
}

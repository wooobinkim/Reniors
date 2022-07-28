package com.common.jmark.domain.entity.user;

import com.common.jmark.domain.entity.Enum.TypeGender;
import com.common.jmark.domain.entity.Enum.TypeIsOpen;
import com.common.jmark.domain.entity.Enum.TypeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String userAppId;

    private String userAppPwd;

    private String kakaoId;

    private String name;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private TypeGender gender;

    private String phone;

    @Enumerated(EnumType.STRING)
    private TypeRole role;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    private String address;

    @Enumerated(EnumType.STRING)
    private TypeIsOpen isOpen;

    private int workingDay;

    private long minSalary;

    private String portfolioName;

    private String portfolioPath;


    public static User create(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, TypeGender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, TypeIsOpen isOpen, int workingDay, long minSalary, String portfolioName, String portfolioPath) {
        User user = new User();
        user.userAppId = userAppId;
        user.userAppPwd = userAppPwd;
        user.kakaoId = kakaoId;
        user.name = name;
        user.birth = birth;
        user.gender = gender;
        user.phone = phone;
        user.role = TypeRole.일반유저;
        user.totalCareer = totalCareer;
        user.profileImgName = profileImgName;
        user.profileImgPath = profileImgPath;
        user.address = address;
        user.isOpen = isOpen;
        user.workingDay = workingDay;
        user.minSalary = minSalary;
        user.portfolioName = portfolioName;
        user.portfolioPath = portfolioPath;
        return user;
    }

    public void update(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, TypeGender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, TypeIsOpen isOpen, int workingDay, long minSalary, String portfolioName, String portfolioPath) {
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
        this.workingDay = workingDay;
        this.minSalary = minSalary;
        this.portfolioName = portfolioName;
        this.portfolioPath = portfolioPath;
    }
}

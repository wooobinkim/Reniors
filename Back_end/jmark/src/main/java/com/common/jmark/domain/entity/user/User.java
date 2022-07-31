package com.common.jmark.domain.entity.user;

import com.common.jmark.domain.entity.Type.Gender;
import com.common.jmark.domain.entity.Type.IsOpen;
import com.common.jmark.domain.entity.Type.Role;
import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.category.Gugun;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String userAppId;

    private String userAppPwd;

    private String kakaoId;

    private String name;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int totalCareer;

    private String profileImgName;

    private String profileImgPath;

    private String address;

    @Enumerated(EnumType.STRING)
    private IsOpen isOpen;

    private int workingDay;

    private long minSalary;

    private String portfolioName;

    private String portfolioPath;

    @OneToMany(mappedBy = "user_board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user_comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> comments = new ArrayList<>();

    public static User create(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, Gender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, IsOpen isOpen, int workingDay, long minSalary, String portfolioName, String portfolioPath) {
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
        user.workingDay = workingDay;
        user.minSalary = minSalary;
        user.portfolioName = portfolioName;
        user.portfolioPath = portfolioPath;
        return user;
    }

    public void update(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, Gender gender, String phone, int totalCareer, String profileImgName, String profileImgPath, String address, IsOpen isOpen, int workingDay, long minSalary, String portfolioName, String portfolioPath) {
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

package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.dto.JobOpeningDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class JobOpening{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_opening_id")
    private Long id;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDate;

    @NotNull
    @CreatedDate
    private LocalDateTime finishedDate;
    @NotNull
    private int numberPeople;
    @NotNull
    private int minCareer;
    @NotNull
    private String title;
    @NotNull
    private String contents;
    private String contentsImgName;
    private String contentsImgPath;
    @NotNull
    private int minSalary;
    private String jobPosition;
    private int workingDay;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEmployment typeEmployment;

    //공고 - 회사 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    //공고 - 고용형태 연관관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_employment_id")
//    private TypeEmployment typeEmployment;

    //최종학력, 직무소분류, 구군 받아오기


    public JobOpening(JobOpeningDto jobOpeningDto) {
        this.id = jobOpeningDto.getId();
        this.createdDate = jobOpeningDto.getCreatedDate();
        this.finishedDate = jobOpeningDto.getFinishedDate();
        this.numberPeople = jobOpeningDto.getNumberPeople();
        this.minCareer = jobOpeningDto.getMinCareer();
        this.title = jobOpeningDto.getTitle();
        this.contents = jobOpeningDto.getContents();
        this.contentsImgName = jobOpeningDto.getContentsImgName();
        this.contentsImgPath = jobOpeningDto.getContentsImgPath();
        this.minSalary = jobOpeningDto.getMinSalary();
        this.jobPosition = jobOpeningDto.getJobPosition();
        this.workingDay = jobOpeningDto.getWorkingDay();
        this.typeEmployment = jobOpeningDto.getTypeEmployment();
    }

    public void updateCompany(Company company){
        this.company = company;
    }

    public void update(JobOpeningDto jobOpeningDto){
        this.numberPeople = jobOpeningDto.getNumberPeople();
        this.minCareer = jobOpeningDto.getMinCareer();
        this.title = jobOpeningDto.getTitle();
        this.contents = jobOpeningDto.getContents();
        this.contentsImgName = jobOpeningDto.getContentsImgName();
        this.contentsImgPath = jobOpeningDto.getContentsImgPath();
        this.minSalary = jobOpeningDto.getMinSalary();
        this.jobPosition = jobOpeningDto.getJobPosition();
        this.workingDay = jobOpeningDto.getWorkingDay();
        this.typeEmployment = jobOpeningDto.getTypeEmployment();
    }
}

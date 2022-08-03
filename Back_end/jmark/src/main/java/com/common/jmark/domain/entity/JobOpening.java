package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class JobOpening{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(length = 2000)
    private String contents;

    @Column(length = 100)
    private String contentsImgName;

    @Column(length = 100)
    private String contentsImgPath;

    private int minSalary;

    @Column(length = 100)
    private String jobPosition;

    private int workingDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEmployment typeEmployment;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    //공고 - 회사 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    private JobChildCategory jobChildCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    private Gugun gugun;

    @OneToMany(mappedBy = "jobOpening")
    List<Apply> applies = new ArrayList<>();

    //공고 - 고용형태 연관관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_employment_id")
//    private TypeEmployment typeEmployment;

    //최종학력, 직무소분류, 구군 받아오기


    public JobOpening(JobOpeningDto jobOpeningDto, Gugun gugun, JobChildCategory jobChildCategory) {
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
        this.lastEdu=jobOpeningDto.getLastEdu();
        this.gugun = gugun;
        this.jobChildCategory = jobChildCategory;

    }

    public void updateCompany(Company company){
        this.company = company;
    }

    public void update(JobOpeningDto jobOpeningDto, Gugun gugun, JobChildCategory jobChildCategory){
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
        this.lastEdu=jobOpeningDto.getLastEdu();
        this.gugun = gugun;
        this.jobChildCategory = jobChildCategory;
    }

}

package com.common.jmark.dto;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class JobOpeningDto {

    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime finishedDate;
    private int numberPeople;
    private int minCareer;
    private String title;
    private String contents;
    private String contentsImgName;
    private String contentsImgPath;
    private int minSalary;
    private String jobPosition;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;
    private GugunResponse gugunResponse;
    private JobChildCategoryResponse jobChildCategoryResponse;
    private CompanyDto companyDto;

    public JobOpeningDto(JobOpening jobOpening,CompanyDto companyDto, GugunResponse gugunResponse, JobChildCategoryResponse jobChildCategoryResponse) {
        this.id = jobOpening.getId();
        this.createdDate = jobOpening.getCreatedDate();
        this.finishedDate = jobOpening.getFinishedDate();
        this.numberPeople = jobOpening.getNumberPeople();
        this.minCareer = jobOpening.getMinCareer();
        this.title = jobOpening.getTitle();
        this.contents = jobOpening.getContents();
        this.contentsImgName = jobOpening.getContentsImgName();
        this.contentsImgPath = jobOpening.getContentsImgPath();
        this.minSalary = jobOpening.getMinSalary();
        this.jobPosition = jobOpening.getJobPosition();
        this.workingDay = jobOpening.getWorkingDay();
        this.typeEmployment = jobOpening.getTypeEmployment();
        this.lastEdu = jobOpening.getLastEdu();
        this.companyDto = companyDto;
        this.gugunResponse = gugunResponse;
        this.jobChildCategoryResponse = jobChildCategoryResponse;
    }

//    public static JobOpeningDto response(JobOpening jobOpening) {
//        return new JobOpeningDto(jobOpening.getId(),
//                jobOpening.getCreatedDate(),
//                jobOpening.getFinishedDate(),
//                jobOpening.getNumberPeople(),
//                jobOpening.getMinCareer(),
//                jobOpening.getTitle(),
//                jobOpening.getContents(),
//                jobOpening.getContentsImgName(),
//                jobOpening.getContentsImgPath(),
//                jobOpening.getMinSalary(),
//                jobOpening.getJobPosition(),
//                jobOpening.getWorkingDay(),
//                jobOpening.getTypeEmployment(),
//                jobOpening.getLastEdu(),
//                jobOpening.getCompany(),
//                jobOpening.getGugun(),
//                jobOpening.getJobChildCategory(),
//                jobOpening.getApplies()
//                );
//    }

//    public void setLinkEntity(CompanyDto companyDto, GugunResponse gugunResponse, JobChildCategoryResponse jobChildCategoryResponse){
//        this.companyDto = companyDto;
//        this.gugunResponse = gugunResponse;
//        this.jobChildCategoryResponse = jobChildCategoryResponse;
//    }
}

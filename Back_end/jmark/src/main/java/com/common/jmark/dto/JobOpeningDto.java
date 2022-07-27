package com.common.jmark.dto;

import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.JobOpening;
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

    public JobOpeningDto(JobOpening jobOpening) {
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
    }
}

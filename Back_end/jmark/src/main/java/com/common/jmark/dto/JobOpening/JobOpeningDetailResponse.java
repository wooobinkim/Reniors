package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.JobOpening;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDetailResponse {
    private Date createdDate;
    private Date finishedDate;
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
    private Long sidoId;
    private Long gugunId;
    private Long jobParentCategory;
    private Long jobChildCategoryId;

    public static JobOpeningDetailResponse response(JobOpening jobOpening){
        return new JobOpeningDetailResponse(
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getNumberPeople(),
                jobOpening.getMinCareer(),
                jobOpening.getTitle(),
                jobOpening.getContents(),
                jobOpening.getContentsImgName(),
                jobOpening.getContentsImgPath(),
                jobOpening.getMinSalary(),
                jobOpening.getJobPosition(),
                jobOpening.getWorkingDay(),
                jobOpening.getTypeEmployment(),
                jobOpening.getLastEdu(),
                jobOpening.getGugun().getSido().getId(),
                jobOpening.getGugun().getId(),
                jobOpening.getJobChildCategory().getParent().getId(),
                jobOpening.getJobChildCategory().getId()
        );
    }
}

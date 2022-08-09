package com.common.jmark.dto.JobOpening;

import com.common.jmark.domain.entity.Type.Employment;
import com.common.jmark.domain.entity.Type.LastEdu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningCreateRequest {

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
    private Employment employment;
    private LastEdu lastEdu;
    private Long gugunId;
    private Long jobChildCategoryId;


}

package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.JobOpening.SearchConditionCreateRequest;
import com.common.jmark.dto.JobOpening.SearchConditionUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCondition {
    @Id
    @GeneratedValue
    @Column(name = "search_condition_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    private int minCareer;

    private int minSalary;

    private int workingDay;

    @Enumerated(EnumType.STRING)
    private TypeEmployment typeEmployment;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    private JobChildCategory jobChildCategory;

    @OneToMany(mappedBy = "searchCondition")
    private List<HopeArea> hopeAreas = new ArrayList<>();

    public SearchCondition(SearchConditionCreateRequest searchConditionCreateRequest,JobChildCategory jobChildCategory, User user) {
        this.name = searchConditionCreateRequest.getName();
        this.minCareer = searchConditionCreateRequest.getMinCareer();
        this.minSalary = searchConditionCreateRequest.getMinSalary();
        this.workingDay = searchConditionCreateRequest.getWorkingDay();
        this.typeEmployment = searchConditionCreateRequest.getTypeEmployment();
        this.lastEdu = searchConditionCreateRequest.getLastEdu();
        this.jobChildCategory = jobChildCategory;
        this.user = user;
    }

    public void update(SearchConditionUpdateRequest searchConditionUpdateRequest, JobChildCategory jobChildCategory, User user) {
        this.name = searchConditionUpdateRequest.getName();
        this.minCareer = searchConditionUpdateRequest.getMinCareer();
        this.minSalary = searchConditionUpdateRequest.getMinSalary();
        this.workingDay = searchConditionUpdateRequest.getWorkingDay();
        this.typeEmployment = searchConditionUpdateRequest.getTypeEmployment();
        this.lastEdu = searchConditionUpdateRequest.getLastEdu();
        this.jobChildCategory = jobChildCategory;
        this.user = user;
    }
}

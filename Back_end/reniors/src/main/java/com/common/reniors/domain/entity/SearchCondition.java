package com.common.reniors.domain.entity;

import com.common.reniors.service.eval.Type.Employment;
import com.common.reniors.service.eval.Type.LastEdu;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.jobOpening.SearchConditionCreateRequest;
import com.common.reniors.dto.jobOpening.SearchConditionUpdateRequest;
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
    private Employment employment;

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
        this.employment = searchConditionCreateRequest.getEmployment();
        this.lastEdu = searchConditionCreateRequest.getLastEdu();
        this.jobChildCategory = jobChildCategory;
        this.user = user;
    }

    public void update(SearchConditionUpdateRequest searchConditionUpdateRequest, JobChildCategory jobChildCategory, User user) {
        this.name = searchConditionUpdateRequest.getName();
        this.minCareer = searchConditionUpdateRequest.getMinCareer();
        this.minSalary = searchConditionUpdateRequest.getMinSalary();
        this.workingDay = searchConditionUpdateRequest.getWorkingDay();
        this.employment = searchConditionUpdateRequest.getEmployment();
        this.lastEdu = searchConditionUpdateRequest.getLastEdu();
        this.jobChildCategory = jobChildCategory;
        this.user = user;
    }
}

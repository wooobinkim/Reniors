package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.LastEdu;
import com.common.jmark.domain.entity.Enum.TypeEmployment;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.SearchConditionDto;
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
    User user;

    @OneToMany(mappedBy = "searchCondition")
    private List<HopeArea> hopeAreas = new ArrayList<>();

    @OneToMany(mappedBy = "searchCondition")
    private List<HopeJob> hopeJobs = new ArrayList<>();

    public SearchCondition(SearchConditionDto searchConditionDto, User user) {
        this.id = searchConditionDto.getId();
        this.name = searchConditionDto.getName();
        this.minCareer = searchConditionDto.getMinCareer();
        this.minSalary = searchConditionDto.getMinSalary();
        this.workingDay = searchConditionDto.getWorkingDay();
        this.typeEmployment = searchConditionDto.getTypeEmployment();
        this.lastEdu = searchConditionDto.getLastEdu();
        this.user = user;
    }

    public void update(SearchConditionDto searchConditionDto, User user) {
        this.name = searchConditionDto.getName();
        this.minCareer = searchConditionDto.getMinCareer();
        this.minSalary = searchConditionDto.getMinSalary();
        this.workingDay = searchConditionDto.getWorkingDay();
        this.typeEmployment = searchConditionDto.getTypeEmployment();
        this.lastEdu = searchConditionDto.getLastEdu();
        this.user = user;
    }
}

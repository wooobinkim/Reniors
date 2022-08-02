package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.dto.HopeJobDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopeJob {
    @Id
    @GeneratedValue
    @Column(name = "hope_job_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_condition_id")
    SearchCondition searchCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    JobChildCategory jobChildCategory;

    public HopeJob(HopeJobDto hopeJobDto, SearchCondition searchCondition, JobChildCategory jobChildCategory) {
        this.id = hopeJobDto.getId();
        this.searchCondition = searchCondition;
        this.jobChildCategory = jobChildCategory;
    }

    public void update(SearchCondition searchCondition, JobChildCategory jobChildCategory) {
        this.searchCondition = searchCondition;
        this.jobChildCategory = jobChildCategory;
    }
}

package com.common.jmark.domain.entity.category;

import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.HopeJob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class JobChildCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_child_category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_parent_category_id")
    private JobParentCategory parent;

    @OneToMany(mappedBy = "jobChildCategory")
    private List<RecommendCondition> recommendConditions = new ArrayList<>();

    @OneToMany(mappedBy = "jobChildCategory")
    private List<HopeJob> hopeJobs = new ArrayList<>();

    public static JobChildCategory create(String name, JobParentCategory parent){
        JobChildCategory jcc = new JobChildCategory();
        jcc.name = name;
        jcc.parent = parent;
        return jcc;
    }

    public void update(String name, JobParentCategory parent){
        this.name = name;
        this.parent = parent;
    }


}

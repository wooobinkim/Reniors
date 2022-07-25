package com.common.jmark.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

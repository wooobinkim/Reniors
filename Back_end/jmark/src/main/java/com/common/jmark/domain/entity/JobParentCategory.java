package com.common.jmark.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class JobParentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_parent_category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobChildCategory> childs = new ArrayList<>();

    public static JobParentCategory create(String name){
        JobParentCategory jpc = new JobParentCategory();
        jpc.name = name;
        return jpc;
    }

    public void update(String name){
        this.name = name;
    }

    public void addJobChildCategory(String childName){
        JobChildCategory jcc = JobChildCategory.create(childName, this);
        childs.add(jcc);
    }
    public void deleteGugun(Long childId){
        Optional<JobChildCategory> findChild = childs.stream()
                .filter(child -> child.getId().equals(childId))
                .findFirst();
        childs.remove(findChild);
    }
}

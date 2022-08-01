package com.common.jmark.domain.entity.category;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.board.Board;
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
public class JobParentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_parent_category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobChildCategory> childs = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();


    public static JobParentCategory create(String name){
        JobParentCategory jpc = new JobParentCategory();
        jpc.name = name;
        return jpc;
    }
    public void update(String name){
        this.name = name;
    }

    public void addChild(String childName){
        JobChildCategory jcc = JobChildCategory.create(childName, this);
        childs.add(jcc);
    }
    public void deleteChild(Long childId){
        JobChildCategory findChild = childs.stream()
                .filter(child -> child.getId().equals(childId))
                .findFirst().orElseThrow(()-> new NotFoundException("Not Found Child Category"));
        childs.remove(findChild);
    }
}

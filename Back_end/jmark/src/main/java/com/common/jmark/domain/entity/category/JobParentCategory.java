package com.common.jmark.domain.entity.category;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.board.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private Long code;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobChildCategory> childs = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    public static JobParentCategory create(String name, Long code){
        JobParentCategory jpc = new JobParentCategory();
        jpc.name = name;
        jpc.code = code;
        return jpc;
    }
    public void update(String name, Long code){
        this.name = name;
        this.code = code;
    }
}

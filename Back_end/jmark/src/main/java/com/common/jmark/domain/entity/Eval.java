package com.common.jmark.domain.entity;

import com.common.jmark.dto.Eval.EvalCreateRequest;
import com.common.jmark.dto.Eval.EvalUpdateRequest;
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
public class Eval {
    @Id
    @GeneratedValue
    @Column(name = "eval_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "eval")
    private List<EvalQuestion> evalQuestions = new ArrayList<>();

    public Eval(EvalCreateRequest evalCreateRequest, Company company) {
        this.name = evalCreateRequest.getName();
        this.company = company;
    }

    public void update(EvalUpdateRequest evalUpdateRequest) {
        this.name = evalUpdateRequest.getName();
    }
}

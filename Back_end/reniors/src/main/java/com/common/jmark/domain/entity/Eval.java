package com.common.jmark.domain.entity;

import com.common.jmark.dto.eval.EvalCreateRequest;
import com.common.jmark.dto.eval.EvalUpdateRequest;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    private JobOpening jobOpening;

    @OneToMany(mappedBy = "eval")
    private List<EvalQuestion> evalQuestions = new ArrayList<>();

    public Eval(EvalCreateRequest evalCreateRequest, Company company, JobOpening jobOpening) {
        this.name = evalCreateRequest.getName();
        this.company = company;
        this.jobOpening = jobOpening;
    }

    public void update(EvalUpdateRequest evalUpdateRequest) {
        this.name = evalUpdateRequest.getName();
    }
}

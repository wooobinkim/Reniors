package com.common.jmark.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class EvalForm {

    @Id @GeneratedValue
    @Column(name = "Eval_id")
    private Long Id;
    private String contents;
    private String score;
    private String memo;

}

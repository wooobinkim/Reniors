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
public class Gugun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_id")
    private Long id;

    private String name;

    private Long gugunCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    public static Gugun create(String name, Long gugunCode, Sido sido){
        Gugun gugun = new Gugun();
        gugun.name = name;
        gugun.gugunCode = gugunCode;
        gugun.sido = sido;
        return gugun;
    }

    public void update(String name, Long gugunCode, Sido sido){
        this.name = name;
        this.gugunCode = gugunCode;
        this.sido = sido;
    }

}

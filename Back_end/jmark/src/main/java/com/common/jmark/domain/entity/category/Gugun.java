package com.common.jmark.domain.entity.category;

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

    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    public static Gugun create(String name, Long code, Sido sido){
        Gugun gugun = new Gugun();
        gugun.name = name;
        gugun.code = code;
        gugun.sido = sido;
        return gugun;
    }

    public void update(String name, Long code, Sido sido){
        this.name = name;
        this.code = code;
        this.sido = sido;
    }

}

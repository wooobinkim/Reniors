package com.common.jmark.domain.entity.category;

import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.HopeArea;
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
public class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_id")
    private Long id;
    private String name;

    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido")
    private Sido sido;

    @OneToMany(mappedBy = "gugun")
    private List<RecommendCondition> recommendConditions = new ArrayList<>();

    @OneToMany(mappedBy = "gugun")
    private  List<HopeArea> hopeAreas = new ArrayList<>();

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

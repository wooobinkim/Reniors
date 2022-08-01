package com.common.jmark.domain.entity.category;

import com.common.exception.NotFoundException;
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
public class Sido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_id")
    private long id;

    private String name;

    private Long code;

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gugun> guguns = new ArrayList<>();

    public static Sido create(String name, long code){
        Sido sido = new Sido();
        sido.name = name;
        sido.code = code;
        return sido;
    }

    public void update(String name, long code){
        this.name = name;
        this.code = code;
    }

    public void addGugun(String gugunName, long gugunCode){
        Gugun gugun = Gugun.create(gugunName, gugunCode, this);
        guguns.add(gugun);
    }

    public void deleteGugun(long gugunId){
        Gugun findGugun = guguns.stream()
                .filter(gugun -> gugun.getId()==(gugunId))
                .findFirst().orElseThrow(()->new NotFoundException("Not Found Gugun"));
        guguns.remove(findGugun);
    }
}

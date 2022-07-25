package com.common.jmark.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private long sidoCode;

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gugun> guguns = new ArrayList<>();

    public static Sido create(String name, long sidoCode){
        Sido sido = new Sido();
        sido.name = name;
        sido.sidoCode = sidoCode;
        return sido;
    }

    public void update(String name, long sidoCode){
        this.name = name;
        this.sidoCode = sidoCode;
    }

    public void addGugun(String gugunName, long gugunCode){
        Gugun gugun = Gugun.create(gugunName, gugunCode, this);
        guguns.add(gugun);
    }

    public void deleteGugun(long gugunId){
        Optional<Gugun> findGugun = guguns.stream()
                .filter(gugun -> gugun.getId()==(gugunId))
                .findFirst();
        guguns.remove(findGugun);
    }

}

package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.dto.HopeAreaDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopeArea {
    @Id @GeneratedValue
    @Column(name = "hope_area_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_condition_id")
    SearchCondition searchCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    Gugun gugun;

    public HopeArea(HopeAreaDto hopeAreaDto, SearchCondition searchCondition, Gugun gugun) {
        this.id = hopeAreaDto.getId();
        this.searchCondition = searchCondition;
        this.gugun = gugun;
    }

    public void update(SearchCondition searchCondition, Gugun gugun) {
        this.searchCondition = searchCondition;
        this.gugun = gugun;
    }
}

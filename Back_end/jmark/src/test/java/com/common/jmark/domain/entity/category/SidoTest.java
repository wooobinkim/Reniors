package com.common.jmark.domain.entity.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SidoTest {
    @Test
    @DisplayName("create sido")
    void create(){
        String name = "대전광역시";
        Long code = 111111L;

        Sido sido = Sido.create(name, code);

        assertThat(sido.getName()).isEqualTo(name);
        assertThat(sido.getCode()).isEqualTo(code);
    }

    @Test
    @DisplayName("update sido")
    void update(){
        Sido sido = Sido.create("대전광역시", 111111L);

        sido.update("서울특별시",222222L);

        assertThat(sido.getName()).isEqualTo("서울특별시");
        assertThat(sido.getCode()).isEqualTo(222222L);
    }
}

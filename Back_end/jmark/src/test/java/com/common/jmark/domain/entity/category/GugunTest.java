package com.common.jmark.domain.entity.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GugunTest {

    @Test
    @DisplayName("create gugun")
    void create() {
        Sido sido = Sido.create("대전광역시", 111111L);
        String name = "유성구";
        Long code = 111112L;
        Gugun gugun = Gugun.create(name, code, sido);

        assertThat(gugun.getSido()).isEqualTo(sido);
        assertThat(gugun.getName()).isEqualTo(name);
        assertThat(gugun.getCode()).isEqualTo(code);
    }

    @Test
    void update() {
        Sido sido = Sido.create("대전광역시", 111111L);
        String name = "유성구";
        Long code = 111112L;
        Gugun gugun = Gugun.create(name, code, sido);

        Sido changeSido = Sido.create("서울특별시", 222222L);
        String changeName = "강남구";
        Long changeCode = 222223L;
        gugun.update(changeName, changeCode, changeSido);

        assertThat(gugun.getSido()).isEqualTo(changeSido);
        assertThat(gugun.getName()).isEqualTo(changeName);
        assertThat(gugun.getCode()).isEqualTo(changeCode);
    }
}
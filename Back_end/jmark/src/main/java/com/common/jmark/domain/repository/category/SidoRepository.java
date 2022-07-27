package com.common.jmark.domain.repository.category;

import com.common.jmark.domain.entity.category.Sido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SidoRepository extends JpaRepository<Sido, Long> {

    Optional<Sido> findByName(String name);
}

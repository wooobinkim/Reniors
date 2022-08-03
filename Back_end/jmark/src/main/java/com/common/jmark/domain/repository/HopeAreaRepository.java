package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.HopeArea;
import com.common.jmark.domain.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeAreaRepository extends JpaRepository<HopeArea, Long> {
    List<HopeArea> findBySearchCondition(SearchCondition searchCondition);
}

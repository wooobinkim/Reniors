package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.HopeArea;
import com.common.jmark.domain.entity.HopeJob;
import com.common.jmark.domain.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeJobRepository extends JpaRepository<HopeJob, Long> {

    List<HopeJob> findBySearchCondition(SearchCondition searchCondition);
}

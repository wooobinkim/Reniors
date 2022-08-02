package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.HopeArea;
import com.common.jmark.domain.entity.HopeJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopeJobRepository extends JpaRepository<HopeJob, Long> {
}

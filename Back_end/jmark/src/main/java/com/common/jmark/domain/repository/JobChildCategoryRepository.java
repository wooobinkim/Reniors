package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.JobChildCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobChildCategoryRepository extends JpaRepository<JobChildCategory, Long> {
    List<JobChildCategory> findAll();
}

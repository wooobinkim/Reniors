package com.common.jmark.domain.repository.category;

import com.common.jmark.domain.entity.category.JobParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobParentCategoryRepository extends JpaRepository<JobParentCategory, Long> {

    Optional<JobParentCategory> findByName(String name);
}

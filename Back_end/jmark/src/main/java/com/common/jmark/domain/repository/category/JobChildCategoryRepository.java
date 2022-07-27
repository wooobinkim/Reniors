package com.common.jmark.domain.repository.category;

import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.category.JobParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobChildCategoryRepository extends JpaRepository<JobChildCategory, Long> {

    Optional<JobChildCategory> findByName(String name);
    List<JobChildCategory> findByParent(JobParentCategory parent);
}

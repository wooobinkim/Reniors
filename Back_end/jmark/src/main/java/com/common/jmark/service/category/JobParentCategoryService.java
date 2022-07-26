package com.common.jmark.service.category;

import com.common.jmark.dto.category.JobParentCategoryCreateRequest;
import com.common.jmark.dto.category.JobParentCategoryResponse;
import com.common.jmark.dto.category.JobParentCategoryUpdateRequest;

import java.util.List;

public interface JobParentCategoryService {
    Long create(JobParentCategoryCreateRequest request);

    void update(Long jpcId, JobParentCategoryUpdateRequest request);

    void delete(Long jpcId);

    List<JobParentCategoryResponse> read();
}

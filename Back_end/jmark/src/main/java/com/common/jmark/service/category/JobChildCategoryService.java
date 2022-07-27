package com.common.jmark.service.category;

import com.common.jmark.dto.category.JobChildCategoryCreateRequest;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.category.JobChildCategoryUpdateRequest;

import java.util.List;

public interface JobChildCategoryService {
    Long create(Long jpcId, JobChildCategoryCreateRequest request);

    void update(Long jccId, JobChildCategoryUpdateRequest request);

    void delete(Long jccId);

    List<JobChildCategoryResponse> read(Long jpcId);
}

package com.common.jmark.service.recommend;

import com.common.jmark.dto.recommend.RecommendConditionCreateRequest;
import com.common.jmark.dto.recommend.RecommendConditionResponse;
import com.common.jmark.dto.recommend.RecommendConditionUpdateRequest;

import java.util.Optional;

public interface RecommendConditionService {
    Long create(Long userId, RecommendConditionCreateRequest request);

    void update(Long recommendConditionId, RecommendConditionUpdateRequest request);

    void delete(Long recommendConditionId);

    RecommendConditionResponse read(Long userId);
}

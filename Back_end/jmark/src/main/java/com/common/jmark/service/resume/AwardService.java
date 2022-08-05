package com.common.jmark.service.resume;

import com.common.jmark.dto.resume.AwardCreateRequest;
import com.common.jmark.dto.resume.AwardResponse;
import com.common.jmark.dto.resume.AwardUpdateRequest;

import java.util.List;

public interface AwardService {
    Long create(Long userId, AwardCreateRequest request);

    void update(Long awardId, AwardUpdateRequest request);

    void delete(Long awardId);

    List<AwardResponse> readList(Long userId);

    AwardResponse read(Long awardId);
}

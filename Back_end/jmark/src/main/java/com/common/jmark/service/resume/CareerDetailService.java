package com.common.jmark.service.resume;

import com.common.jmark.dto.resume.CareerDetailCreateRequest;
import com.common.jmark.dto.resume.CareerDetailResponse;
import com.common.jmark.dto.resume.CareerDetailUpdateRequest;

import java.util.List;

public interface CareerDetailService {
    Long create(Long userId, CareerDetailCreateRequest request);

    void update(Long careerDetailId, CareerDetailUpdateRequest request);

    void delete(Long careerDetailId);

    List<CareerDetailResponse> read(Long userId);
}

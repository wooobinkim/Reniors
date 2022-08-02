package com.common.jmark.service.category;


import com.common.jmark.dto.category.GugunCreateRequest;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.GugunUpdateRequest;

import java.util.List;

public interface GugunService {
    Long create(Long sidoId, GugunCreateRequest request);

    void update(Long gugunId, GugunUpdateRequest request);

    void delete(Long gugunId);

    List<GugunResponse> read(Long sidoId);
}

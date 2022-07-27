package com.common.jmark.service.category;

import com.common.jmark.dto.category.SidoCreateRequest;
import com.common.jmark.dto.category.SidoResponse;
import com.common.jmark.dto.category.SidoUpdateRequest;

import java.util.List;

public interface SidoService {
    Long create(SidoCreateRequest request);

    void update(Long sidoId, SidoUpdateRequest request);

    void delete(Long sidoId);

    List<SidoResponse> read();
}

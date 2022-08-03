package com.common.jmark.service.resume;

import com.common.jmark.dto.resume.LicenseCreateRequest;
import com.common.jmark.dto.resume.LicenseResponse;
import com.common.jmark.dto.resume.LicenseUpdateRequest;

import java.util.List;

public interface LicenseService {
    Long create(Long userId, LicenseCreateRequest request);

    void update(Long licenseId, LicenseUpdateRequest request);

    void delete(Long licenseId);

    List<LicenseResponse> read(Long userId);
}

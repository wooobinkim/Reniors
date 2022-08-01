package com.common.jmark.service.resume;

import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.resume.Award;
import com.common.jmark.domain.entity.resume.License;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.resume.LicenseRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.resume.LicenseCreateRequest;
import com.common.jmark.dto.resume.LicenseResponse;
import com.common.jmark.dto.resume.LicenseUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.LICENSE_NOT_FOUND;
import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseServiceImpl implements LicenseService {
    private final UserRepository userRepository;

    private final LicenseRepository licenseRepository;

    @Override
    @Transactional
    // 같은 이름의 자격증은 없다는 가정 하에 중복을 허용하지 않음 -> 37~39, 49~51 line 수정 필요
    public Long create(Long userId, LicenseCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
//        if (!licenseRepository.findById(userId).get().getName().equals(request.getName())) {
//            throw new DuplicateException(String.format("%s는 이미 등록된 자격증입니다.", request.getName()));
//        }
        License license = License.create(request.getName(), request.getPassedAt(), request.getGrade(), user);
        return licenseRepository.save(license).getId();
    }

    @Override
    @Transactional
    public void update(Long licenseId, LicenseUpdateRequest request) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(()->new NotFoundException(LICENSE_NOT_FOUND));
//        if (licenseRepository.findById(licenseId).get().getName().equals(request.getName())) {
//            throw new DuplicateException(String.format("%s는 이미 등록된 자격증입니다.", request.getName()));
//        }
        license.update(request.getName(), request.getPassedAt(), request.getGrade());
    }

    @Override
    @Transactional
    public void delete(Long licenseId) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(()->new NotFoundException(LICENSE_NOT_FOUND));
        licenseRepository.delete(license);
    }

    @Override
    @Transactional
    public List<LicenseResponse> read(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<LicenseResponse> licenses = licenseRepository.findByUser(user).stream()
                .map(LicenseResponse::response)
                .collect(Collectors.toList());
        return licenses;
    }
}

package com.common.jmark.controller;

import com.common.jmark.dto.resume.*;
import com.common.jmark.service.resume.AwardService;
import com.common.jmark.service.resume.CareerDetailService;
import com.common.jmark.service.resume.LicenseService;
import com.common.jmark.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@Api(tags={"이력서 API"})
public class ResumeController {
    private final AwardService awardService;
    private final CareerDetailService careerDetailService;
    private final LicenseService licenseService;

    @ApiOperation(value = "경력사항 추가", notes = "기업명, 재직 시작 날짜, 재직 종료 날짜, 직무 및 업무내용, 해당 User 정보를 서버에 넘겨주세요")
    @ApiImplicitParam(name = "request", value = "경력사항의 기업명, 재직 시작 날짜, 재직 종료 날짜, 직무 및 업무내용, 해당 User의 userId")
    @PostMapping("/{userId}/career")
    public ResponseEntity<?> createCareer (
            @PathVariable Long userId,
            @Valid @RequestBody CareerDetailCreateRequest request
            ) {
        Long careerDetailId = careerDetailService.create(userId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("careerDetailId", careerDetailId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/career")
    public ResponseEntity<?> readCareer (
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(careerDetailService.read(userId));
    }

    @PutMapping("/{userId}/career/{careerDetailId}")
    public ResponseEntity<?> updateCareer (
//            @PathVariable Long userId,
            @PathVariable Long careerDetailId,
            @Valid @RequestBody CareerDetailUpdateRequest request
            ) {
        careerDetailService.update(careerDetailId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{userId}/career/{careerDetailId}")
    public ResponseEntity<Map<String, Long>> deleteCareer (
//            @PathVariable Long userId,
            @PathVariable Long careerDetailId
    ) {
        careerDetailService.delete(careerDetailId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "수상경력 추가", notes = "수상경력명, 수상날짜, 해당 User의 userId를 서버에 넘겨주세요")
    @ApiImplicitParam(name = "request", value = "수상경력의 수상경력명, 수상날짜, 해당 User의 userId")
    @PostMapping("/{userId}/awards")
    public ResponseEntity<?> createAward(
            @PathVariable Long userId,
            @Valid @RequestBody AwardCreateRequest request
    ) {
        Long awardId = awardService.create(userId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("awardId", awardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/awards")
    public ResponseEntity<?> readAward (
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(awardService.read(userId));
    }

    @PutMapping("/{userId}/awards/{awardId}")
    public ResponseEntity<?> updateAward (
//            @PathVariable Long userId,
            @PathVariable Long awardId,
            @Valid @RequestBody AwardUpdateRequest request
    ) {
        awardService.update(awardId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{userId}/awards/{awardId}")
    public ResponseEntity<Map<String, Long>> deleteAward (
//            @PathVariable Long userId,
            @PathVariable Long awardId
    ) {
        awardService.delete(awardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "자격증 추가", notes = "자격증명, 취득일자, 자격증 등급, 해당 User의 userId를 서버에 넘겨주세요")
    @ApiImplicitParam(name = "request", value = "수상경력의 자격증명, 취득일자, 자격증 등급, 해당 User의 userId")
    @PostMapping("/{userId}/license")
    public ResponseEntity<?> createLicense (
            @PathVariable Long userId,
            @Valid @RequestBody LicenseCreateRequest request
    ) {
        Long licenseId = licenseService.create(userId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("licenseId", licenseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/license")
    public ResponseEntity<?> readLicense (
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(licenseService.read(userId));
    }

    @PutMapping("/{userId}/license/{licenseId}")
    public ResponseEntity<?> updateLicense (
//            @PathVariable Long userId,
            @PathVariable Long licenseId,
            @Valid @RequestBody LicenseUpdateRequest request
    ) {
        licenseService.update(licenseId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/{userId}/license/{licenseId}")
    public ResponseEntity<Map<String, Long>> deleteLicense (
//            @PathVariable Long userId,
            @PathVariable Long licenseId
    ) {
        licenseService.delete(licenseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.common.jmark.controller;

import com.common.jmark.dto.recommend.RecommendConditionCreateRequest;
import com.common.jmark.dto.recommend.RecommendConditionUpdateRequest;
import com.common.jmark.service.recommend.RecommendConditionService;
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
@RequiredArgsConstructor
@RequestMapping("/recommend")
@Api(tags={"추천 조건 API"})
public class RecommendConditionController {
    private final RecommendConditionService recommendConditionService;

    @ApiOperation(value = "추천 조건 생성", notes = "직무 소분류 ID, 구군 ID, 희망 근무 일수, 의망 최소 연봉을 서버에 넘겨주세요")
    @ApiImplicitParam(name = "request", value = "")
    @PostMapping("/{userId}/condition")
    public ResponseEntity<?> create (
            @PathVariable Long userId,
            @Valid @RequestBody RecommendConditionCreateRequest request
    ) {
        Long recommendConditionId = recommendConditionService.create(userId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("RecommendCondition", recommendConditionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/condition")
    public ResponseEntity<?> read(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(recommendConditionService.read(userId));
    }

    @PutMapping("/condition/{recommendConditionId}")
    public ResponseEntity<?> update(
            @PathVariable Long recommendConditionId,
            @Valid @RequestBody RecommendConditionUpdateRequest request
    ) {
        recommendConditionService.update(recommendConditionId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/condition/{recommendConditionId}")
    public ResponseEntity<Map<String, Long>> delete(
            @PathVariable Long recommendConditionId
    ) {
        recommendConditionService.delete(recommendConditionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
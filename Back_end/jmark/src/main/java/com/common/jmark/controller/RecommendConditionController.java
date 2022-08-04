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

    @PostMapping("/{userId}/condition")
    @ApiOperation(value = "추천 조건 생성", notes = "해당 유저의 추천 조건을 생성합니다.")
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
    @ApiOperation(value = "추천 조건 조회", notes = "해당 유저의 추천 조건을 조회합니다.")
    public ResponseEntity<?> read(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(recommendConditionService.read(userId));
    }

    @PutMapping("/condition/{recommendConditionId}")
    @ApiOperation(value = "추천 조건 수정", notes = "해당 유저의 추천 조건을 수정합니다.")
    public ResponseEntity<?> update(
            @PathVariable Long recommendConditionId,
            @Valid @RequestBody RecommendConditionUpdateRequest request
    ) {
        recommendConditionService.update(recommendConditionId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/condition/{recommendConditionId}")
    @ApiOperation(value = "추천 조건 삭제", notes = "해당 유저의 추천 조건을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> delete(
            @PathVariable Long recommendConditionId
    ) {
        recommendConditionService.delete(recommendConditionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
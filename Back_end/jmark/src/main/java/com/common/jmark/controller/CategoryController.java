package com.common.jmark.controller;

import com.common.jmark.dto.category.*;
import com.common.jmark.service.category.GugunService;
import com.common.jmark.service.category.JobChildCategoryService;
import com.common.jmark.service.category.JobParentCategoryService;
import com.common.jmark.service.category.SidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final SidoService sidoService;
    private final GugunService gugunService;

    private final JobChildCategoryService jobChildCategoryService;
    private final JobParentCategoryService jobParentCategoryService;

    @PostMapping("/sido")
    public ResponseEntity<Map<String, Long>> createSido(
            @Valid @RequestBody SidoCreateRequest request){
        Long sidoId = sidoService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("sidoId", sidoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sido")
    public ResponseEntity<List<SidoResponse>> readSido(){
        return ResponseEntity.ok(sidoService.read());
    }

    @PutMapping("/sido/{sidoId}")
    public ResponseEntity<Map<String, Long>> updateSido(
            @PathVariable Long sidoId,
            @Valid @RequestBody SidoUpdateRequest request){
        sidoService.update(sidoId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sido/{sidoId}")
    public ResponseEntity<Map<String, Long>> deleteSido(
            @PathVariable Long sidoId){
        sidoService.delete(sidoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sido/{sidoId}/gugun")
    public ResponseEntity<Map<String, Long>> createGugun(
            @PathVariable Long sidoId,
            @Valid @RequestBody GugunCreateRequest request){
        Long gugunId = gugunService.create(sidoId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("gugunId", gugunId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sido/{sidoId}/gugun")
    public ResponseEntity<List<GugunResponse>> readGugun(
            @PathVariable Long sidoId){
        return ResponseEntity.ok(gugunService.read(sidoId));
    }

    @PutMapping("/sido/{sidoId}/gugun/{gugunId}")
    public ResponseEntity<Map<String, Long>> updateGugun(
            @PathVariable("sidoId") Long sidoId,
            @PathVariable("gugunId") Long gugunId,
            @Valid @RequestBody GugunUpdateRequest request){
        gugunService.update(gugunId,request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sido/{sidoId}/gugun/{gugunId}")
    public ResponseEntity<Map<String, Long>> deleteGugun(
            @PathVariable Long gugunId){
        gugunService.delete(gugunId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/parent")
    public ResponseEntity<Map<String, Long>> createParent(
            @Valid @RequestBody JobParentCategoryCreateRequest request){
        Long parentId = jobParentCategoryService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("parentId", parentId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/parent")
    public ResponseEntity<List<JobParentCategoryResponse>> readParent(){
        return ResponseEntity.ok(jobParentCategoryService.read());
    }
    @PutMapping("/parent/{parentId}")
    public ResponseEntity<Map<String, Long>> updateParent(
            @PathVariable Long parentId,
            @Valid @RequestBody JobParentCategoryUpdateRequest request){
        jobParentCategoryService.update(parentId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/parent/{parentId}")
    public ResponseEntity<Map<String, Long>> deleteParent(
            @PathVariable Long parentId){
        jobParentCategoryService.delete(parentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/parent/{parentId}/child")
    public ResponseEntity<Map<String, Long>> createChild(
            @PathVariable Long parentId,
            @Valid @RequestBody JobChildCategoryCreateRequest request){
        Long childId = jobChildCategoryService.create(parentId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("childId", childId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/parent/{parentId}/child")
    public ResponseEntity<List<JobChildCategoryResponse>> readChild(
            @PathVariable Long parentId){
        return ResponseEntity.ok(jobChildCategoryService.read(parentId));
    }
    @PutMapping("/parent/{parentId}/child/{childId}")
    public ResponseEntity<Map<String, Long>> updateChild(
            @PathVariable Long parentId,
            @PathVariable Long childId,
            @Valid @RequestBody JobChildCategoryUpdateRequest request){
        jobChildCategoryService.update(childId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/parent/{parentId}/child/{childId}")
    public ResponseEntity<Map<String, Long>> deleteChild(
            @PathVariable Long parentId,
            @PathVariable Long childId){
        jobChildCategoryService.delete(childId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.common.jmark.controller;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.JobOpeningSearchDto;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.service.JobOpeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobopening")
@RequiredArgsConstructor
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    //채용공고 조회(조건포함)
    @GetMapping
    public ResponseEntity<?> getJobOpeningList(@RequestBody JobOpeningSearchDto jobOpeningSearchDto, Pageable pageable){
        Page<JobOpeningDto> jobOpeningList = jobOpeningService.getJobOpeningList(jobOpeningSearchDto, pageable);
//        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(x->new JobOpeningDto(x)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }


    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        /*
        토큰에서 회사 or 유저 정보추출
         */

        JobOpeningDto JobOpening = jobOpeningService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //채용공고 등록
    @PostMapping
    public ResponseEntity<?> postJobOpening(@RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpening jobOpening = jobOpeningService.postJobOpening(1L,jobOpeningDto);
        JobOpeningDto JobOpening = new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //채용공고 수정
    @PutMapping("/{jobOpeningId}")
    public ResponseEntity<?> updateJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId, @RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpening jobOpening = jobOpeningService.updateJobOpening(jobOpeningId, jobOpeningDto);
        JobOpeningDto JobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //채용공고 삭제
    @DeleteMapping("/{jobOpeningId}")
    public ResponseEntity<?> deleteJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        /*
        토큰에서 회사정보추출
         */

        jobOpeningService.deleteJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}

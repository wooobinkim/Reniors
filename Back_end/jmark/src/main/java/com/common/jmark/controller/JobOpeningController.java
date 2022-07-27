package com.common.jmark.controller;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.JobOpeningSearchDto;
import com.common.jmark.service.JobOpeningService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getJobOpeningList(@RequestBody JobOpeningSearchDto jobOpeningSearchDto){
        List<JobOpening> jobOpeningList = jobOpeningService.getJobOpeningList(jobOpeningSearchDto);
        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(x->new JobOpeningDto(x)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningDtoList);
    }


    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        /*
        토큰에서 회사 or 유저 정보추출
         */

        JobOpening jobOpening = jobOpeningService.getJobOpening(jobOpeningId);
        JobOpeningDto getJobOpening = new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(getJobOpening);
    }

    //채용공고 등록
    @PostMapping
    public ResponseEntity<?> postJobOpening(@RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpening jobOpening = jobOpeningService.postJobOpening(1L,jobOpeningDto);
        JobOpeningDto postJobOpening = new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(postJobOpening);
    }

    //채용공고 수정
    @PutMapping("/{jobOpeningId}")
    public ResponseEntity<?> updateJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId, @RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpening jobOpening = jobOpeningService.updateJobOpening(jobOpeningId, jobOpeningDto);
        JobOpeningDto updateJobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(updateJobOpening);
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

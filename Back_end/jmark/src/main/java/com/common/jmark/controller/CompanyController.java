package com.common.jmark.controller;

import com.common.jmark.common.config.security.filter.JwtAuthenticationFilter;
import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.config.web.LoginCompany;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.CompanyLoginRequest;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JwtUtil jwtUtil;

    //회사 회원가입
    @PostMapping
    public ResponseEntity<?> postCompany(@RequestBody CompanyDto companyDto){
        System.out.println("companyDto = " + companyDto);
        CompanyDto Company = companyService.postCompany(companyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Company);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCompany(@RequestBody CompanyLoginRequest companyLoginRequest){
        String accessToken = companyService.loginCompany(companyLoginRequest);
        System.out.println("accessToken = " + accessToken);
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION,accessToken).build();
    }

    //회사 상세정보
    @GetMapping()
    public ResponseEntity<?> getCompany(@LoginCompany Company company){
//        token = token.substring(7);
//        String subject = jwtUtil.getSubject(token);
//
        CompanyDto Company = companyService.getCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사정보 수정
    @PutMapping()
    public ResponseEntity<?> updateCompany(@RequestBody CompanyDto companyDto){
        CompanyDto Company = companyService.updateCompany(5L, companyDto);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사 탈퇴
    @DeleteMapping()
    public ResponseEntity<?> deleteCompany(){
        companyService.deleteCompany(1L);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 등록
    @PostMapping("/jobopening")
    public ResponseEntity<?> postJobOpening(@RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpeningDto JobOpening = companyService.postJobOpening(1L, jobOpeningDto);
//        JobOpeningDto JobOpening = new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.CREATED).body(JobOpening);
    }

    //회사 공고 목록
    @GetMapping("/jobopening")
    public ResponseEntity<?> getJobOpeningList(){
        List<JobOpeningDto> JobOpeningList = companyService.getJobOpeningList(1L);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpeningList);
    }

    //회사 공고 상세조회
    @GetMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        /*
        토큰에서 회사 or 유저 정보추출
         */

        JobOpeningDto JobOpening = companyService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 수정
    @PutMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> updateJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId, @RequestBody JobOpeningDto jobOpeningDto){
        /*
        토큰에서 회사정보추출
         */

        JobOpeningDto JobOpening = companyService.updateJobOpening(jobOpeningId, jobOpeningDto);
//        JobOpeningDto JobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 삭제
    @DeleteMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> deleteJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        /*
        토큰에서 회사정보추출
         */

        companyService.deleteJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 지원자 목록
    //회사 공고 지원자 상세목록
    //회사 공고 지원자 상태수정
}

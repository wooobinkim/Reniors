package com.common.jmark.controller;

import com.common.jmark.common.config.security.filter.JwtAuthenticationFilter;
import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.config.web.LoginCompany;
import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.ApplyDto;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.CompanyLoginRequest;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.user.UserResponse;
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
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION,accessToken).build();
    }

    //회사 상세정보
    @GetMapping()
    public ResponseEntity<?> getCompany(@LoginCompany Company company){
        CompanyDto Company = companyService.getCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사정보 수정
    @PutMapping()
    public ResponseEntity<?> updateCompany(@LoginCompany Company company, @RequestBody CompanyDto companyDto){
        CompanyDto Company = companyService.updateCompany(company, companyDto);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사 탈퇴
    @DeleteMapping()
    public ResponseEntity<?> deleteCompany(@LoginCompany Company company){
        companyService.deleteCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 등록
    @PostMapping("/jobopening")
    public ResponseEntity<?> postJobOpening(@LoginCompany Company company, @RequestBody JobOpeningDto jobOpeningDto){

        JobOpeningDto JobOpening = companyService.postJobOpening(company, jobOpeningDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(JobOpening);
    }

    //회사 공고 목록
    @GetMapping("/jobopening")
    public ResponseEntity<?> getJobOpeningList(@LoginCompany Company company){
        List<JobOpeningDto> JobOpeningList = companyService.getJobOpeningList(company);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpeningList);
    }

    //회사 공고 상세조회
    @GetMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> getJobOpening(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDto JobOpening = companyService.getJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 수정
    @PutMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> updateJobOpening(@LoginCompany Company company,
                                              @PathVariable("jobOpeningId") Long jobOpeningId,
                                              @RequestBody JobOpeningDto jobOpeningDto){

        JobOpeningDto JobOpening = companyService.updateJobOpening(company, jobOpeningId, jobOpeningDto);
//        JobOpeningDto JobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 삭제
    @DeleteMapping("/jobopening/{jobOpeningId}")
    public ResponseEntity<?> deleteJobOpening(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.deleteJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 지원자 목록
    @GetMapping("/jobopening/{jobOpeningId}/apply")
    public ResponseEntity<?> getApplyList(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        List<ApplyDto> ApplyList = companyService.getappliyList(company, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //회사 공고 지원자 상세목록
    @GetMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    public ResponseEntity<?> getApplicant(@LoginCompany Company company, @PathVariable("applyId") Long applyId){
        UserResponse User = companyService.getapplicant(company, applyId);
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

    //회사 공고 지원자 상태수정
    @PutMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    public ResponseEntity<?> updateApply(@LoginCompany Company company,
                                         @PathVariable("applyId") Long applyId,
                                         @RequestBody ApplyDto applyDto){
        System.out.println("company = " + company);
        System.out.println("applyId = " + applyId);
        System.out.println("applyDto = " + applyDto);
        Apply apply = companyService.updateapply(company, applyId, applyDto);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

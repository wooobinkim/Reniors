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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags={"회사 API"})
public class CompanyController {
    private final CompanyService companyService;
    private final JwtUtil jwtUtil;

    //회사 회원가입
    @PostMapping
    @ApiOperation(value = "회사 회원가입", notes = "회사아이디로 회원가입을 한다.")
    public ResponseEntity<?> postCompany(@RequestBody CompanyDto companyDto){
        System.out.println("companyDto = " + companyDto);
        CompanyDto Company = companyService.postCompany(companyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Company);
    }

    @PostMapping("/login")
    @ApiOperation(value = "회사 로그인", notes = "회사 아이디로 로그인을 한다.")
    public ResponseEntity<?> loginCompany(@RequestBody CompanyLoginRequest companyLoginRequest){
        String accessToken = companyService.loginCompany(companyLoginRequest);
        System.out.println("accessToken = " + accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION,accessToken).build();
    }

    //회사 상세정보
    @GetMapping()
    @ApiOperation(value = "회사 상세정보", notes = "회사 상세정보를 가져온다.")
    public ResponseEntity<?> getCompany(@LoginCompany Company company){
        CompanyDto Company = companyService.getCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사정보 수정
    @PutMapping()
    @ApiOperation(value = "회사 정보수정", notes = "회사 정보를 수정한다.")
    public ResponseEntity<?> updateCompany(@LoginCompany Company company, @RequestBody CompanyDto companyDto){
        CompanyDto Company = companyService.updateCompany(company, companyDto);

        return ResponseEntity.status(HttpStatus.OK).body(Company);
    }


    //회사 탈퇴
    @DeleteMapping()
    @ApiOperation(value = "회사 탈퇴", notes = "회사 아이디 탈퇴한다.")
    public ResponseEntity<?> deleteCompany(@LoginCompany Company company){
        companyService.deleteCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 등록
    @PostMapping("/jobopening")
    @ApiOperation(value = "회사 공고 등록", notes = "회사가 공고를 등록한다.")
    public ResponseEntity<?> postJobOpening(@LoginCompany Company company, @RequestBody JobOpeningDto jobOpeningDto){

        JobOpeningDto JobOpening = companyService.postJobOpening(company, jobOpeningDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(JobOpening);
    }

    //회사 공고 목록
    @GetMapping("/jobopening")
    @ApiOperation(value = "회사 공고목록", notes = "회사가 올린 공고 목록들을 가져온다.")
    public ResponseEntity<?> getJobOpeningList(@LoginCompany Company company){
        List<JobOpeningDto> JobOpeningList = companyService.getJobOpeningList(company);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpeningList);
    }

    //회사 공고 상세조회
    @GetMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고상세조회", notes = "회사가 올린 공고 하나의 상세정보를 가져온다.")
    public ResponseEntity<?> getJobOpening(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDto JobOpening = companyService.getJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 수정
    @PutMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고수정", notes = "회사가 올린 공고를 수정한다.")
    public ResponseEntity<?> updateJobOpening(@LoginCompany Company company,
                                              @PathVariable("jobOpeningId") Long jobOpeningId,
                                              @RequestBody JobOpeningDto jobOpeningDto){

        JobOpeningDto JobOpening = companyService.updateJobOpening(company, jobOpeningId, jobOpeningDto);
//        JobOpeningDto JobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);
    }

    //회사 공고 삭제
    @DeleteMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고삭제", notes = "회사가 올린 공고를 삭제한다.")
    public ResponseEntity<?> deleteJobOpening(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.deleteJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //회사 공고 지원자 목록
    @GetMapping("/jobopening/{jobOpeningId}/apply")
    @ApiOperation(value = "공고 지원자 목록", notes = "올린 공고의 지원자 목록을 가져온다.")
    public ResponseEntity<?> getApplyList(@LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        List<ApplyDto> ApplyList = companyService.getappliyList(company, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //회사 공고 지원자 상세목록
    @GetMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상세목록", notes = "올린 공고의 지원자 중 한명의 정보를 가져온다.")
    public ResponseEntity<?> getApplicant(@LoginCompany Company company, @PathVariable("applyId") Long applyId){
        UserResponse User = companyService.getapplicant(company, applyId);
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

    //회사 공고 지원자 상태수정
    @PutMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상태수정", notes = "올린 공고의 지원자들의 정보를 수정한다")
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

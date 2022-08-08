package com.common.jmark.controller;

import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.config.web.LoginCompany;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.Apply.ApplyResponse;
import com.common.jmark.dto.Apply.ApplyUpdateRequest;
import com.common.jmark.dto.Company.CompanyCreateRequest;
import com.common.jmark.dto.Company.CompanyResponse;
import com.common.jmark.dto.Company.CompanyUpdateRequest;
import com.common.jmark.dto.Company.CompanyLoginRequest;
import com.common.jmark.dto.JobOpening.*;

import com.common.jmark.dto.user.UserResponse;
import com.common.jmark.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Api(tags={"회사 API"})
public class CompanyController {
    private final CompanyService companyService;
    private final JwtUtil jwtUtil;

    //회사 회원가입
    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation(value = "회사 회원가입", notes = "회사아이디로 회원가입을 한다.")
    public ResponseEntity<?> postCompany(
            @RequestPart(value = "img", required = false) MultipartFile file,
            @RequestPart(value = "data")  CompanyCreateRequest companyCreateRequest) throws Exception {
        Long companyId = companyService.postCompany(companyCreateRequest);
        if(file != null) {
            // TODO : 파일경로 수정
            //File dest = new File("C:/temp/image/" + companyCreateRequest.getCompanyNum());
            File dest = new File("/home/ubuntu/images/company/" + companyId);
            file.transferTo(dest);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyId);
    }
    @PostMapping("/login")
    @ApiOperation(value = "회사 로그인", notes = "회사 아이디로 로그인을 한다.")
    public ResponseEntity<?> loginCompany(@RequestBody CompanyLoginRequest companyLoginRequest){
        String accessToken = companyService.loginCompany(companyLoginRequest);

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION,accessToken).build();
    }

    //회사 상세정보
    @GetMapping()
    @ApiOperation(value = "회사 상세정보", notes = "회사 상세정보를 가져온다.")

    public ResponseEntity<?> getCompany(@ApiIgnore @LoginCompany Company company){
        CompanyResponse companyResponse = companyService.getCompany(company);


        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }


    //회사정보 수정
    @PutMapping()
    @ApiOperation(value = "회사 정보수정", notes = "회사 정보를 수정한다.")

    public ResponseEntity<?> updateCompany(@ApiIgnore @LoginCompany Company company, @RequestBody CompanyUpdateRequest companyUpdateRequest){
       companyService.updateCompany(company, companyUpdateRequest);


        return ResponseEntity.status(HttpStatus.OK).body("success");
    }


    //회사 탈퇴
    @DeleteMapping()
    @ApiOperation(value = "회사 탈퇴", notes = "회사 아이디 탈퇴한다.")
    public ResponseEntity<?> deleteCompany(@ApiIgnore @LoginCompany Company company){
        companyService.deleteCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 등록
    @PostMapping("/jobopening")
    @ApiOperation(value = "회사 공고 등록", notes = "회사가 공고를 등록한다.")
    public ResponseEntity<?> postJobOpening(@ApiIgnore @LoginCompany Company company, @RequestBody JobOpeningCreateRequest jobOpeningCreateRequest){
        Long jobOpeningId = companyService.postJobOpening(company, jobOpeningCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobOpeningId);
    }

    //회사 공고 목록
    @GetMapping("/jobopening")
    @ApiOperation(value = "회사 공고목록", notes = "회사가 올린 공고 목록들을 가져온다.")

    public ResponseEntity<?> getJobOpeningList(@ApiIgnore @LoginCompany Company company){

        List<JobOpeningCompanyResponse> jobOpeningList = companyService.getJobOpeningList(company);


        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //회사 공고 상세조회
    @GetMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고상세조회", notes = "회사가 올린 공고 하나의 상세정보를 가져온다.")

    public ResponseEntity<?> getJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDetailResponse jobOpening = companyService.getJobOpening(company, jobOpeningId);


        return ResponseEntity.status(HttpStatus.OK).body(jobOpening);
    }

    //회사 공고 수정
    @PutMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고수정", notes = "회사가 올린 공고를 수정한다.")
    public ResponseEntity<?> updateJobOpening(@ApiIgnore @LoginCompany Company company,
                                              @PathVariable("jobOpeningId") Long jobOpeningId,
                                              @RequestBody JobOpeningUpdateRequest jobOpeningUpdateRequest){

       companyService.updateJobOpening(company, jobOpeningId, jobOpeningUpdateRequest);
//        JobOpeningDto JobOpening= new JobOpeningDto(jobOpening);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 삭제
    @DeleteMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고삭제", notes = "회사가 올린 공고를 삭제한다.")
    public ResponseEntity<?> deleteJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.deleteJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 끝내기
    @PutMapping("/jobopening/{jobOpeningId}/finish")
    @ApiOperation(value = "회사 공고끝내기", notes = "회사가 올린 공고를 끝낸다.")
    public ResponseEntity<?> finishJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.finishJobOpening(company,jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 지원자 목록
    @GetMapping("/jobopening/{jobOpeningId}/apply")
    @ApiOperation(value = "공고 지원자 목록", notes = "올린 공고의 지원자 목록을 가져온다.")

    public ResponseEntity<?> getApplyList(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        List<ApplyResponse> applyList = companyService.getappliyList(company, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(applyList);
    }

    //회사 공고 지원자 상세목록
    @GetMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상세목록", notes = "올린 공고의 지원자 중 한명의 정보를 가져온다.")
    public ResponseEntity<?> getApplicant( @ApiIgnore @LoginCompany Company company, @PathVariable("applyId") Long applyId){
        UserResponse User = companyService.getapplicant(company, applyId);
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

    //회사 공고 지원자 상태수정
    @PutMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상태수정", notes = "올린 공고의 지원자들의 정보를 수정한다")
    public ResponseEntity<?> updateApply( @ApiIgnore @LoginCompany Company company,
                                         @PathVariable("applyId") Long applyId,
                                         @RequestBody ApplyUpdateRequest applyUpdateRequest){

        companyService.updateapply(company, applyId, applyUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}

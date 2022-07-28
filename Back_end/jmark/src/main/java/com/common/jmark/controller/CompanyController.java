package com.common.jmark.controller;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/hi")
    public ResponseEntity<?> hi(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("hi");
    }

    //회사 회원가입
    @PostMapping
    public ResponseEntity<?> postCompany(@RequestBody CompanyDto companyDto){
        System.out.println("companyDto = " + companyDto);
        CompanyDto postCompany = companyService.postCompany(companyDto);

        return ResponseEntity.status(HttpStatus.OK).body(postCompany);
    }

    //회사 상세정보
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable("companyId")Long companyId){
        CompanyDto getCompany = companyService.getCompany(companyId);

        return ResponseEntity.status(HttpStatus.OK).body(getCompany);
    }

    //회사정보 수정
    @PutMapping("/{companyId}")
    public ResponseEntity<?> updateCompany(@PathVariable("companyId")Long companyId, @RequestBody CompanyDto companyDto){
        CompanyDto updateCompany = companyService.updateCompany(companyId, companyDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateCompany);
    }


    //회사 탈퇴
    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable("companyId")Long companyId){
        companyService.deleteCompany(companyId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

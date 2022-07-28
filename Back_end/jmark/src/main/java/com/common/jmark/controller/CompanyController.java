package com.common.jmark.controller;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/hi")
    public ResponseEntity<?> hi(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("hiz");
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable("companyId")Long companyId){
        Company company = companyService.getCompany(companyId);

        CompanyDto companyDto = new CompanyDto(company);

        return ResponseEntity.status(HttpStatus.OK).body(companyDto);
    }
}

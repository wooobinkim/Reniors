package com.common.jmark.service;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    //회사 회원가입
    @Transactional
    public CompanyDto postCompany(CompanyDto companyDto){
        Company company = new Company(companyDto);
        companyRepository.save(company);

        CompanyDto companyDto1 = new CompanyDto(company);
        return companyDto1;
    }

    //회사 상세정보
    @Transactional
    public CompanyDto getCompany(Long companyId){
        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isPresent()){
            CompanyDto companyDto = new CompanyDto(company.get());
            return companyDto;
        }

        return null;
    }

    //회사 정보수정
    @Transactional
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()){
            companyOptional.get().update(companyDto);

            CompanyDto companyDto1 = new CompanyDto(companyOptional.get());
            return companyDto1;
        }

        return null;
    }

    //회사 탈퇴퇴
    @Transactional
    public void deleteCompany(Long companyId){
        companyRepository.deleteById(companyId);
    }

}

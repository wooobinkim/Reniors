package com.common.jmark.service;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public Company getCompany(Long companyId){
        Optional<Company> company = companyRepository.findById(companyId);

        return company.get();
    }


}

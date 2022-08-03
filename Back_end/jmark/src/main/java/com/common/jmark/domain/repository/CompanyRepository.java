package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.JobOpeningDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
//    List<JobOpeningDto> findByCompanyId(Long companyId);
    Optional<Company> findByCompanyAppId (String companyAppId);
}

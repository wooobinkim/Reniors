package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyAppId (String companyAppId);
}

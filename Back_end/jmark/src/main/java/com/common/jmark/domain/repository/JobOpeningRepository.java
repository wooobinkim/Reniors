package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long>{

    List<JobOpening> findByCompany(Company company);
//        Page<JobOpening> pageJobOpeningList(JobOpeningSearchDto jobOpeningSearchDto, Pageable pageable);
            List<JobOpening> findByCompanyId(Long companyId);
}

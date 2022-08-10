package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long>{

    List<JobOpening> findByCompany(Company company);
//        Page<JobOpening> pageJobOpeningList(JobOpeningSearchDto jobOpeningSearchDto, Pageable pageable);
            List<JobOpening> findByCompanyId(Long companyId);
            List<JobOpening> findTop10ByOrderByViewsDesc();
}

package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.dto.JobOpeningSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long>{

//    @Query("select j from JobOpening j where j.contents like %:JobOpeningSearchDto.getContents()% and j.min_salary >= :JobOpeningSearchDto.salary")
//    List<JobOpening> getJobOpeningList(@Param("JobOpeningSearchDto") JobOpeningSearchDto jobOpeningSearchDto);
}

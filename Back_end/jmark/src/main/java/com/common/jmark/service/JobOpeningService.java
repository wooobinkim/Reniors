package com.common.jmark.service;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.QJobOpening;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.JobOpeningRepository;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.JobOpeningSearchDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;

    @PersistenceContext
    EntityManager em;


    @Transactional
    public List<JobOpening> getJobOpeningList(@RequestBody JobOpeningSearchDto jobOpeningSearchDto){

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j).where(j.contents.contains(jobOpeningSearchDto.getContents())).where(j.minSalary.goe(jobOpeningSearchDto.getSalary())).fetch();
        if (jobOpeningList.size() != 0){
            return  jobOpeningList;
        }else{
            return null;
        }
    }

    //채용공고 상세조회
    @Transactional
    public JobOpening getJobOpening(Long jobOpeningId){
        Optional<JobOpening> jobOpening = jobOpeningRepository.findById(jobOpeningId);
        if (jobOpening.isPresent()){
            return jobOpening.get();
        }else {
            return null;
        }
    }

    //채용공고 등록
    @Transactional
    public JobOpening postJobOpening(Long companyId,JobOpeningDto jobOpeningDto){
        JobOpening jobOpening = new JobOpening(jobOpeningDto);
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()){
            jobOpening.updateCompany(company.get());
            jobOpeningRepository.save(jobOpening);
            return jobOpening;
        }else {
            return null;
        }
    }

    //채용공고 수정
    @Transactional
    public JobOpening updateJobOpening(Long jobOpeningId, JobOpeningDto jobOpeningDto){
        Optional<JobOpening> jobOpening = jobOpeningRepository.findById(jobOpeningId);

        if (jobOpening.isPresent()){
            jobOpening.get().update(jobOpeningDto);
            return jobOpening.get();
        }else{
            return null;
        }
    }

    //채용공고 삭제
    @Transactional
    public void deleteJobOpening(Long jobOpeningId){
        jobOpeningRepository.deleteById(jobOpeningId);
    }
}

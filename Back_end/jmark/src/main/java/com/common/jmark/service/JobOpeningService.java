package com.common.jmark.service;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.QJobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.JobOpeningRepository;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.JobOpeningSearchDto;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Page<JobOpeningDto> getJobOpeningList(@RequestBody JobOpeningSearchDto jobOpeningSearchDto, Pageable pageable){
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (jobOpeningSearchDto.getGuGunId() != null) booleanBuilder.and(j.gugun.id.eq(jobOpeningSearchDto.getGuGunId()));
        if (jobOpeningSearchDto.getJobChildCategoryId()!= null) booleanBuilder.and(j.jobChildCategory.id.eq(jobOpeningSearchDto.getJobChildCategoryId()));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        (j.contents.contains(jobOpeningSearchDto.getContents())),
                        (j.minSalary.goe(jobOpeningSearchDto.getMinSalary())),
                        booleanBuilder
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0){
            List<JobOpeningDto> jobOpeningDtoList = new ArrayList<>();

            for (JobOpening jobOpening : jobOpeningList) {
                //연결된 엔티티 매핑
                GugunResponse gugunResponse = GugunResponse.response(jobOpening.getGugun());
                JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening.getJobChildCategory());
                CompanyDto companyDto = new CompanyDto(jobOpening.getCompany());

                //리턴할 Dto 세팅
                JobOpeningDto jobOpeningDto = new JobOpeningDto(jobOpening);
                jobOpeningDto.setLinkEntity(companyDto,gugunResponse,jobChildCategoryResponse);

                jobOpeningDtoList.add(jobOpeningDto);
            }

            long total = jobOpeningDtoList.size();

            Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList,pageable,total);

            return jobOpeningDtoPage;

        }else{
            return null;
        }
    }

    //채용공고 상세조회
    @Transactional
    public JobOpeningDto getJobOpening(Long jobOpeningId){
        Optional<JobOpening> jobOpening = jobOpeningRepository.findById(jobOpeningId);
        if (jobOpening.isPresent()){
            JobOpening jobOpening1 = jobOpening.get();

            //연결된 엔티티 매핑
            GugunResponse gugunResponse = GugunResponse.response(jobOpening1.getGugun());
            JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening1.getJobChildCategory());
            CompanyDto companyDto = new CompanyDto(jobOpening1.getCompany());

            //리턴할 Dto 세팅
            JobOpeningDto jobOpeningDto = new JobOpeningDto(jobOpening1);
            jobOpeningDto.setLinkEntity(companyDto,gugunResponse,jobChildCategoryResponse);

            return jobOpeningDto;
        }else {
            return null;
        }
    }

    //채용공고 등록
    @Transactional
    public JobOpening postJobOpening(Long companyId,JobOpeningDto jobOpeningDto){
        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        JobOpening jobOpening = new JobOpening(jobOpeningDto,gugun,jobChildCategory);
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
        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        Optional<JobOpening> jobOpening = jobOpeningRepository.findById(jobOpeningId);

        if (jobOpening.isPresent()){
            jobOpening.get().update(jobOpeningDto,gugun,jobChildCategory);
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

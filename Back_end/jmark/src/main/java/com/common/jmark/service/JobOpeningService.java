package com.common.jmark.service;

import com.common.jmark.common.exception.NotFoundException;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;

    @PersistenceContext
    EntityManager em;

    //채용공고 조건 전체조회
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
//            List<JobOpeningDto> jobOpeningDtoList = new ArrayList<>();
            List<JobOpeningDto> jobOpeningDtoList =
                    jobOpeningList.stream().map(o->new JobOpeningDto(
                            o,
                            new CompanyDto(o.getCompany()),
                            GugunResponse.response(o.getGugun()),
                            JobChildCategoryResponse.response(o.getJobChildCategory())
                    )).collect(Collectors.toList());


//            for (JobOpening jobOpening : jobOpeningList) {
//                //연결된 엔티티 매핑
//                GugunResponse gugunResponse = GugunResponse.response(jobOpening.getGugun());
//                JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening.getJobChildCategory());
//                CompanyDto companyDto = new CompanyDto(jobOpening.getCompany());
//
//                //리턴할 Dto 세팅
//                JobOpeningDto jobOpeningDto = new JobOpeningDto(jobOpening,companyDto,gugunResponse,jobChildCategoryResponse);
////                jobOpeningDto.setLinkEntity(companyDto,gugunResponse,jobChildCategoryResponse);
//
//                jobOpeningDtoList.add(jobOpeningDto);
//            }

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
        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found jobOpening"));

        if (optionalJobOpening.isPresent()){
            JobOpening jobOpening1 = optionalJobOpening.get();

            //연결된 엔티티 매핑
            GugunResponse gugunResponse = GugunResponse.response(jobOpening1.getGugun());
            JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening1.getJobChildCategory());
            CompanyDto companyDto = new CompanyDto(jobOpening1.getCompany());

            //리턴할 Dto 세팅
            JobOpeningDto jobOpeningDto = new JobOpeningDto(jobOpening1,companyDto,gugunResponse,jobChildCategoryResponse);
//            jobOpeningDto.setLinkEntity(companyDto,gugunResponse,jobChildCategoryResponse);
            return jobOpeningDto;
        }else {
            return null;
        }
    }

}

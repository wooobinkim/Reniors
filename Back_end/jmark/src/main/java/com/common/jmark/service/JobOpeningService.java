package com.common.jmark.service;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.*;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.*;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.dto.*;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.user.UserResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
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
    private final ApplyRepository applyRepository;
    private final HopeJobRepository hopeJobRepository;
    private final HopeAreaRepository hopeAreaRepository;
    private final SearchConditionRepository searchConditionRepository;

    @PersistenceContext
    EntityManager em;

    //공고 조건 생성(지역, 직무 제외)
    @Transactional
    public SearchConditionDto postSearchCondition(User user, SearchConditionDto searchConditionDto){
        SearchCondition searchCondition = new SearchCondition(searchConditionDto, user);
        SearchConditionDto searchConditionDto1 = new SearchConditionDto(searchCondition);

        searchConditionRepository.save(searchCondition);

        return searchConditionDto1;
    }
    //공고 조건 희망지역 설정
    @Transactional
    public void postHopeArea(Long searchConditionId,HopeAreaDto hopeAreaDto){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaDto.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));

        HopeArea hopeArea = new HopeArea(hopeAreaDto,searchCondition,gugun);
        hopeAreaRepository.save(hopeArea);
    }
    //공고 조건 희망지역 수정
    @Transactional
    public void updateHopeArea(Long searchConditionId, HopeAreaDto hopeAreaDto, Long hopeAreaId){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaDto.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));
        HopeArea hopeArea = hopeAreaRepository.findById(hopeAreaId).orElseThrow(() -> new NotFoundException("not found area"));

        hopeArea.update(searchCondition,gugun);
    }


    //공고 조건 희망지역 삭제
    @Transactional
    public void deleteHopeArea(Long hopeAreaId){

        hopeAreaRepository.deleteById(hopeAreaId);
    }

    //공고 조건 희망직무 설정
    @Transactional
    public void postHopeJob(Long searchConditionId,HopeJobDto hopeJobDto){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobDto.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));

        HopeJob hopeJob = new HopeJob(hopeJobDto,searchCondition,jobChildCategory);
        hopeJobRepository.save(hopeJob);
    }
    //공고 조건 희망지역 수정
    @Transactional
    public void updateHopeJob(Long searchConditionId, HopeJobDto hopeJobDto, Long hopeJobId){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobDto.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));
        HopeJob hopejob = hopeJobRepository.findById(hopeJobId).orElseThrow(() -> new NotFoundException("not found hopejob"));

        hopejob.update(searchCondition,jobChildCategory);
    }

    //공고 조건 희망지역 삭제
    @Transactional
    public void deleteHopeJob(Long hopeJobId){

        hopeJobRepository.deleteById(hopeJobId);
    }

    //공고 조건 조회
    @Transactional
    public List<SearchConditionDto> getSearchConditionList(User user){
        List<SearchCondition> searchConditionList = searchConditionRepository.findByUser(user);
        List<SearchConditionDto> searchConditionDtoList = searchConditionList.stream().map(s->new SearchConditionDto(
                s
        )).collect(Collectors.toList());

        return searchConditionDtoList;
    }

    //공고 조건 수정
    @Transactional
    public void updateSearchCondition(User user, Long searchConditionId,SearchConditionDto searchConditionDto){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        searchCondition.update(searchConditionDto,user);
    }

    //공고 조건 삭제
    @Transactional
    public void deleteSearchCondition(Long searchConditionId){
        searchConditionRepository.deleteById(searchConditionId);
    }

    //공고 조건 상세조회
    public SearchConditionDto getSearchCondition(Long searchConditionId){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        SearchConditionDto searchConditionDto = new SearchConditionDto(searchCondition);

        return searchConditionDto;
    }

    @Transactional
    //공고 전체조회
    public Page<JobOpeningDto> getJobOpening(Pageable pageable){
        System.out.println("들어옴?????????????????????");
        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(j->new JobOpeningDto(
                j,
                new CompanyDto(j.getCompany()),
                GugunResponse.response(j.getGugun()),
                JobChildCategoryResponse.response(j.getJobChildCategory())
        )).collect(Collectors.toList());

        long total = jobOpeningDtoList.size();

        Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList,pageable,total);

        return jobOpeningDtoPage;
    }

    //채용공고 조건 조회
    @Transactional
    public Page<JobOpeningDto> getJobOpeningConditionList(Long searchConditionId, Pageable pageable){
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        List<HopeArea> hopeAreaList = hopeAreaRepository.findBySearchCondition(searchCondition);
        List<HopeJob> hopeJobList = hopeJobRepository.findBySearchCondition(searchCondition);

        List<Gugun> gugunList = new ArrayList<>();
         for (HopeArea hopeArea : hopeAreaList) {
             Gugun gugun = gugunRepository.findById(hopeArea.getGugun().getId()).orElseThrow(() -> new NotFoundException("not found gugun"));
             gugunList.add(gugun);
        }

         List<JobChildCategory> jobChildCategoryList = new ArrayList<>();
        for (HopeJob hopeJob : hopeJobList) {
            JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJob.getJobChildCategory().getId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));
            jobChildCategoryList.add(jobChildCategory);
        }
//        for (HopeArea hopeArea : hopeAreaList) {
//            booleanBuilder.and(j.gugun.id.in(hopeArea.getId()));
//        }
//
//        for (HopeJob hopeJob : hopeJobList) {
//            booleanBuilder.and(j.jobChildCategory.id.in(hopeJob.getId()));
//        }
//        if (jobOpeningSearchDto.getGuGunId() != null) booleanBuilder.and(j.gugun.id.eq(jobOpeningSearchDto.getGuGunId()));
//        if (jobOpeningSearchDto.getJobChildCategoryId()!= null) booleanBuilder.and(j.jobChildCategory.id.eq(jobOpeningSearchDto.getJobChildCategoryId()));
            if (searchCondition.getLastEdu() != null) booleanBuilder.and(j.lastEdu.eq(searchCondition.getLastEdu()));
            if (searchCondition.getTypeEmployment() != null) booleanBuilder.and(j.typeEmployment.eq(searchCondition.getTypeEmployment()));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
//                        (j.contents.contains(jobOpeningSearchDto.getContents())),
//                        (j.minSalary.goe(jobOpeningSearchDto.getMinSalary())),
//                        booleanBuilder
                        (j.minCareer.goe(searchCondition.getMinCareer())),
                        (j.minSalary.goe(searchCondition.getMinSalary())),
                        (j.workingDay.loe(searchCondition.getWorkingDay())),
                        booleanBuilder,
//                        (j.typeEmployment.eq(searchCondition.getTypeEmployment())),
//                        (j.lastEdu.eq(searchCondition.getLastEdu())),
                        (j.gugun.in(gugunList)),
                        (j.jobChildCategory.in(jobChildCategoryList))
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

    //지원하기
    @Transactional
    public ApplyDto applyJobOpening(User user, Long jobOpeningId, ApplyDto applyDto){
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        Apply apply = new Apply(applyDto, user,jobOpening);
        applyRepository.save(apply);

        return applyDto;
    }

    //지원이력 조회
    @Transactional
    public List<ApplyDto> getApplyList(User user){
        List<Apply> applyList = applyRepository.findByUser(user);

        List<ApplyDto> applyDtoList = applyList.stream().map(a->new ApplyDto(
                a,
                new JobOpeningDto(a.getJobOpening(),
                        new CompanyDto(a.getJobOpening().getCompany()),
                        GugunResponse.response(a.getJobOpening().getGugun()),
                        JobChildCategoryResponse.response(a.getJobOpening().getJobChildCategory())),
                UserResponse.response(user)
        )).collect(Collectors.toList());

        return applyDtoList;
    }

    //지원이력 상세조회
    @Transactional
    public ApplyDto getApply(User user, Long applyId){
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found apply"));

        JobOpeningDto jobOpeningDto = new JobOpeningDto(apply.getJobOpening(),
                new CompanyDto(apply.getJobOpening().getCompany()),
                GugunResponse.response(apply.getJobOpening().getGugun()),
                JobChildCategoryResponse.response(apply.getJobOpening().getJobChildCategory()));

        ApplyDto applyDto = new ApplyDto(apply,jobOpeningDto,UserResponse.response(user));

        return applyDto;
    }

}

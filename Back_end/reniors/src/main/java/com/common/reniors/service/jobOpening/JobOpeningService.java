package com.common.reniors.service.jobOpening;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.*;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.*;
import com.common.reniors.domain.repository.category.GugunRepository;
import com.common.reniors.domain.repository.category.JobChildCategoryRepository;
import com.common.reniors.domain.repository.recommend.RecommendConditionRepository;
import com.common.reniors.dto.apply.ApplyResponse;
import com.common.reniors.dto.jobOpening.*;
import com.common.reniors.dto.category.GugunResponse;
import com.common.reniors.dto.category.JobChildCategoryResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final HopeAreaRepository hopeAreaRepository;
    private final SearchConditionRepository searchConditionRepository;
    private final RecommendConditionRepository recommendConditionRepository;

    @PersistenceContext
    EntityManager em;

    //공고 조건 생성(지역, 직무 제외)
    @Transactional
    public Long postSearchCondition(User user, SearchConditionCreateRequest searchConditionCreateRequest){
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(searchConditionCreateRequest.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jcc"));
        SearchCondition searchCondition = new SearchCondition(searchConditionCreateRequest,jobChildCategory, user);

        Long id = searchConditionRepository.save(searchCondition).getId();

        return id;
    }
    //공고 조건 희망지역 설정
    @Transactional
    public Long postHopeArea(Long searchConditionId, HopeAreaCreateRequest hopeAreaCreateRequest){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaCreateRequest.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));

        HopeArea hopeArea = new HopeArea(hopeAreaCreateRequest,searchCondition,gugun);
        Long id = hopeAreaRepository.save(hopeArea).getId();

        return id;
    }
    //공고 조건 희망지역 수정
    @Transactional
    public void updateHopeArea(Long searchConditionId, HopeAreaUpdateRequest hopeAreaUpdateRequest, Long hopeAreaId){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaUpdateRequest.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));
        HopeArea hopeArea = hopeAreaRepository.findById(hopeAreaId).orElseThrow(() -> new NotFoundException("not found area"));

        hopeArea.update(searchCondition,gugun);
    }


    //공고 조건 희망지역 삭제
    @Transactional
    public void deleteHopeArea(Long hopeAreaId){

        hopeAreaRepository.deleteById(hopeAreaId);
    }

//    //공고 조건 희망직무 설정
//    @Transactional
//    public void postHopeJob(Long searchConditionId,HopeJobDto hopeJobDto){
//        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
//        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobDto.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));
//
//        HopeJob hopeJob = new HopeJob(hopeJobDto,searchCondition,jobChildCategory);
//        hopeJobRepository.save(hopeJob);
//    }
//    //공고 조건 희망직무 수정
//    @Transactional
//    public void updateHopeJob(Long searchConditionId, HopeJobDto hopeJobDto, Long hopeJobId){
//        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
//        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobDto.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));
//        HopeJob hopejob = hopeJobRepository.findById(hopeJobId).orElseThrow(() -> new NotFoundException("not found hopejob"));
//
//        hopejob.update(searchCondition,jobChildCategory);
//    }
//
//    //공고 조건 희망직무 삭제
//    @Transactional
//    public void deleteHopeJob(Long hopeJobId){
//
//        hopeJobRepository.deleteById(hopeJobId);
//    }

    //공고 조건 조회
    @Transactional
    public List<SearchConditionResponse> getSearchConditionList(User user){
        List<SearchCondition> searchConditionList = searchConditionRepository.findByUser(user);
        List<SearchConditionResponse> searchConditionResponseList = searchConditionList.stream().map(s->
                SearchConditionResponse.response(s)
        ).collect(Collectors.toList());

        return searchConditionResponseList;
    }

    //공고 조건 수정
    @Transactional
    public void updateSearchCondition(User user, Long searchConditionId, SearchConditionUpdateRequest searchConditionUpdateRequest){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(searchConditionUpdateRequest.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jcc"));
        searchCondition.update(searchConditionUpdateRequest, jobChildCategory,user);
    }

    //공고 조건 삭제
    @Transactional
    public void deleteSearchCondition(Long searchConditionId){
        searchConditionRepository.deleteById(searchConditionId);
    }

    //공고 조건 상세조회
    public SearchConditionResponse getSearchCondition(Long searchConditionId){
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));

        SearchConditionResponse searchConditionResponse = SearchConditionResponse.response(searchCondition);

        return searchConditionResponse;
    }

    @Transactional
    //공고 전체조회
    public Page<JobOpeningResponse> getJobOpening(Pageable pageable){
        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();

        List<JobOpeningResponse> jobOpeningResponses = jobOpeningList.stream().map(j->JobOpeningResponse.response(
                j
        )).collect(Collectors.toList());

        long total = jobOpeningResponses.size();

        Page jobOpeningDtoPage = new PageImpl<>(jobOpeningResponses,pageable,total);

        return jobOpeningDtoPage;
    }

    //공고 전체조회(조회수 탑10)
    public List<JobOpeningResponse> getJobOpeningViewDesc(){
        List<JobOpening> jobOpeningList = jobOpeningRepository.findTop10ByOrderByViewsDesc();


        List<JobOpeningResponse> jobOpeningResponses = jobOpeningList.stream().map(j->JobOpeningResponse.response(
                j
        )).collect(Collectors.toList());

//        long total = jobOpeningResponses.size();

//        Page jobOpeningDtoPage = new PageImpl<>(jobOpeningResponses,pageable,total);

        return jobOpeningResponses;
    }

    //채용공고 조회(조건포함)
    @Transactional
    public Page<JobOpeningResponse> getJobOpeningConditionList(User user,Long searchConditionId, Pageable pageable){
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        List<HopeArea> hopeAreaList = hopeAreaRepository.findBySearchCondition(searchCondition);

        List<Gugun> gugunList = new ArrayList<>();
         for (HopeArea hopeArea : hopeAreaList) {
             Gugun gugun = gugunRepository.findById(hopeArea.getGugun().getId()).orElseThrow(() -> new NotFoundException("not found gugun"));
             gugunList.add(gugun);
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
                        (j.jobChildCategory.eq(searchCondition.getJobChildCategory())),
                        booleanBuilder,
//                        (j.typeEmployment.eq(searchCondition.getTypeEmployment())),
//                        (j.lastEdu.eq(searchCondition.getLastEdu())),
                        (j.gugun.in(gugunList))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0){
//            List<JobOpeningDto> jobOpeningDtoList = new ArrayList<>();
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o->JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());

            long total = jobOpeningDtoList.size();

            Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList,pageable,total);

            return jobOpeningDtoPage;

        }else{
            return null;
        }
    }

    //채용공고 조회(추천공고)
    @Transactional
    public Page<JobOpeningResponse> getJobOpeningRecommendList(User user,Long recommendConditionId, Pageable pageable){
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        RecommendCondition rc = recommendConditionRepository.findById(recommendConditionId).orElseThrow(() -> new NotFoundException("not found rc"));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        (j.minSalary.goe(rc.getMinSalary())),
                        (j.workingDay.loe(rc.getWorkingDay())),
                        (j.jobChildCategory.eq(rc.getJobChildCategory())),
                        (j.gugun.eq(rc.getGugun()))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0){
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o->JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());

            long total = jobOpeningDtoList.size();

            Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList,pageable,total);

            return jobOpeningDtoPage;

        }else{
            return null;
        }
    }

    //채용공고 조회(추천공고, 조회수 탑10)
    @Transactional
    public List<JobOpeningResponse> getJobOpeningListRecommendViewDesc(User user,Long recommendConditionId){
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        RecommendCondition rc = recommendConditionRepository.findById(recommendConditionId).orElseThrow(() -> new NotFoundException("not found rc"));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        (j.minSalary.goe(rc.getMinSalary())),
                        (j.workingDay.loe(rc.getWorkingDay())),
                        (j.jobChildCategory.eq(rc.getJobChildCategory())),
                        (j.gugun.eq(rc.getGugun()))
                )
                .offset(0)
                .limit(10)
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0){
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o->JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());


            return jobOpeningDtoList;

        }else{
            return null;
        }
    }

    //채용공고 상세조회
    @Transactional
    public JobOpeningDetailResponse getJobOpening(Long jobOpeningId){
        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found jobOpening"));

        if (optionalJobOpening.isPresent()){
            JobOpening jobOpening1 = optionalJobOpening.get();
            jobOpening1.viewUp();
            //연결된 엔티티 매핑
            GugunResponse gugunResponse = GugunResponse.response(jobOpening1.getGugun());
            JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening1.getJobChildCategory());

            //리턴할 Dto 세팅
            JobOpeningDetailResponse jobOpeningDetailResponse = JobOpeningDetailResponse.response(jobOpening1);
//            jobOpeningDto.setLinkEntity(companyDto,gugunResponse,jobChildCategoryResponse);
            return jobOpeningDetailResponse;
        }else {
            return null;
        }
    }

    //지원하기
    @Transactional
    public Long applyJobOpening(User user, Long jobOpeningId){
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        Apply apply = new Apply(user,jobOpening);
        Long id = applyRepository.save(apply).getId();

        return id;
    }

    //지원이력 조회
    @Transactional
    public List<ApplyResponse> getApplyList(User user){
        List<Apply> applyList = applyRepository.findByUser(user);

        List<ApplyResponse> applyResponseList = applyList.stream().map(a->ApplyResponse.response(
                a
                )
        ).collect(Collectors.toList());

        return applyResponseList;
    }

    //지원이력 상세조회
    @Transactional
    public ApplyResponse getApply(User user, Long applyId){
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found apply"));
//        JobOpeningDto jobOpeningDto = new JobOpeningDto(apply.getJobOpening(),
//                new CompanyDto(apply.getJobOpening().getCompany()),
//                GugunResponse.response(apply.getJobOpening().getGugun()),
//                JobChildCategoryResponse.response(apply.getJobOpening().getJobChildCategory()));

        ApplyResponse applyResponse = ApplyResponse.response(apply);
//        ApplyDto applyDto = new ApplyDto(apply,jobOpeningDto,UserResponse.response(user));

        return applyResponse;
    }

}

package com.common.jmark.service;

import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.exception.NotAuthException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.common.exception.NotMatchException;
import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.repository.ApplyRepository;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.JobOpeningRepository;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.dto.ApplyDto;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.CompanyLoginRequest;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotAuthException.COMPANY_NOT_MATCH;
import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;
import static com.common.jmark.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
    private final ApplyRepository applyRepository;
    private final JwtUtil jwtUtil;

    //회사 회원가입
    @Transactional
    public CompanyDto postCompany(CompanyDto companyDto){
        Company company = new Company(companyDto);
        companyRepository.save(company);

        CompanyDto companyDto1 = new CompanyDto(company);
        return companyDto1;
    }

    @Transactional
    public String loginCompany(CompanyLoginRequest companyLoginRequest){
        Optional<Company> optionalCompany = companyRepository.findByCompanyAppId(companyLoginRequest.getCompanyAppId());
        if (!optionalCompany.isPresent()){
            throw new NotFoundException(USER_NOT_FOUND);
        }else {
            if (optionalCompany.get().getCompanyAppPwd().equals(companyLoginRequest.getCompanyAppPwd())){
                return jwtUtil.createToken(optionalCompany.get().getId(), "company");
            }else {
                throw new NotMatchException(PASSWORD_NOT_MATCH);
            }
        }
    }

    //회사 상세정보
    @Transactional
    public CompanyDto getCompany(Company company){
        Optional<Company> optionalCompany = companyRepository.findById(company.getId());
        optionalCompany.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if (optionalCompany.isPresent()){
            CompanyDto companyDto = new CompanyDto(optionalCompany.get());
            return companyDto;
        }else {
            return null;
        }
    }

    //회사 정보수정
    @Transactional
    public CompanyDto updateCompany(Company company, CompanyDto companyDto){
//        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));
//        if (optionalCompany.isPresent()){
            company.update(companyDto);
            CompanyDto companyDto1 = new CompanyDto(company);
            return companyDto1;
//        }else {
//            return null;
//        }
    }

    //회사 탈퇴
    @Transactional
    public void deleteCompany(Company company){
//        Optional<Company> optionalCompany = companyRepository.findById(company.getId());
//        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));
        companyRepository.deleteById(company.getId());
    }

    //회사 공고 등록
    @Transactional
    public JobOpeningDto postJobOpening(Company company, JobOpeningDto jobOpeningDto){
        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        JobOpening jobOpening = new JobOpening(jobOpeningDto,gugun,jobChildCategory);
//        Optional<Company> company = companyRepository.findById(companyId);
//        if(company.isPresent()){
            jobOpening.updateCompany(company);
            jobOpeningRepository.save(jobOpening);

            CompanyDto companyDto = new CompanyDto(company);

            JobOpeningDto jobOpeningDto1 = new JobOpeningDto(jobOpening,
                    companyDto,
                    GugunResponse.response(gugun),
                    JobChildCategoryResponse.response(jobChildCategory));

            return jobOpeningDto1;
//        }else {
//            return null;
//        }
    }

    //회사 공고 목록
    @Transactional
    public List<JobOpeningDto> getJobOpeningList(Company company){
//        Optional<Company> optionalCompany = companyRepository.findById(companyId);
//        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));

//        if (optionalCompany.isPresent()){
            List<JobOpening> jobOpeningList = jobOpeningRepository.findByCompany(company);
            List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(j->new JobOpeningDto(
                    j,
                    new CompanyDto(j.getCompany()),
                    GugunResponse.response(j.getGugun()),
                    JobChildCategoryResponse.response(j.getJobChildCategory())
            )).collect(Collectors.toList());

            return jobOpeningDtoList;
//        }else {
//            return null;
//        }

    }

    //회사 공고 상세조회
    @Transactional
    public JobOpeningDto getJobOpening(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

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

    //회사 공고 수정
    @Transactional
    public JobOpeningDto updateJobOpening(Company company, Long jobOpeningId, JobOpeningDto jobOpeningDto){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found jobOpening"));

        if (optionalJobOpening.isPresent()){
            optionalJobOpening.get().update(jobOpeningDto,gugun,jobChildCategory);

//            Optional<Company> optionalCompany = companyRepository.findById(1L);
            CompanyDto companyDto = new CompanyDto(company);

            JobOpeningDto jobOpeningDto1 = new JobOpeningDto(optionalJobOpening.get(),
                    companyDto,
                    GugunResponse.response(gugun),
                    JobChildCategoryResponse.response(jobChildCategory));

            return jobOpeningDto1;
        }else{
            return null;
        }
    }

    //회사 공고 삭제
    @Transactional
    public void deleteJobOpening(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found JobOpening"));

        jobOpeningRepository.deleteById(jobOpeningId);
    }

    //회사 공고 지원자 목록
    @Transactional
    public List<ApplyDto> getappliyList(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found Apply"));
        JobOpeningDto jobOpeningDto = new JobOpeningDto(
                jobOpening,
                new CompanyDto(company),
                GugunResponse.response(jobOpening.getGugun()),
                JobChildCategoryResponse.response(jobOpening.getJobChildCategory())
                );

        List<Apply> applyList = applyRepository.findByJobOpening(jobOpening);
        List<ApplyDto> applyDtoList = applyList.stream().map(a->new ApplyDto(
                a,
                jobOpeningDto,
                UserResponse.response(a.getUser())
        )).collect(Collectors.toList());

        return  applyDtoList;
    }

    //회사 공고 지원자 상세목록
    @Transactional
    public UserResponse getapplicant(Company company, Long applyId){
        if(company.getId() != applyRepository.findById(applyId).get().getJobOpening().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found Apply"));
        UserResponse userResponse = UserResponse.response(apply.getUser());

        return userResponse;
    }

    //회사 공고 지원자 상태수정
    @Transactional
    public Apply updateapply(Company company, Long applyId, ApplyDto applyDto){
        if(company.getId() != applyRepository.findById(applyId).get().getJobOpening().getCompany().getId())
            throw new NotAuthException(COMPANY_NOT_MATCH);

        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found Apply"));
        apply.update(applyDto,apply.getUser(), apply.getJobOpening());

        return apply;

    }

}

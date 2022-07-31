package com.common.jmark.service;

import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.common.exception.NotMatchException;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.JobOpeningRepository;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.CompanyLoginRequest;
import com.common.jmark.dto.JobOpeningDto;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;
import static com.common.jmark.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
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
                return jwtUtil.createToken(optionalCompany.get().getId());
            }else {
                throw new NotMatchException(PASSWORD_NOT_MATCH);
            }
        }
    }

    //회사 상세정보
    @Transactional
    public CompanyDto getCompany(Long companyId){
        Optional<Company> optionalCompany = companyRepository.findById(1L);
        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));

        if (optionalCompany.isPresent()){
            CompanyDto companyDto = new CompanyDto(optionalCompany.get());
            return companyDto;
        }else {
            return null;
        }
    }

    //회사 정보수정
    @Transactional
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));

        if (optionalCompany.isPresent()){
            optionalCompany.get().update(companyDto);
            CompanyDto companyDto1 = new CompanyDto(optionalCompany.get());
            return companyDto1;
        }else {
            return null;
        }
    }

    //회사 탈퇴
    @Transactional
    public void deleteCompany(Long companyId){
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));

        companyRepository.deleteById(companyId);
    }

    //회사 공고 등록
    @Transactional
    public JobOpeningDto postJobOpening(Long companyId, JobOpeningDto jobOpeningDto){
        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        JobOpening jobOpening = new JobOpening(jobOpeningDto,gugun,jobChildCategory);
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()){
            jobOpening.updateCompany(company.get());
            jobOpeningRepository.save(jobOpening);

            CompanyDto companyDto = new CompanyDto(company.get());

            JobOpeningDto jobOpeningDto1 = new JobOpeningDto(jobOpening,
                    companyDto,
                    GugunResponse.response(gugun),
                    JobChildCategoryResponse.response(jobChildCategory));

            return jobOpeningDto1;
        }else {
            return null;
        }
    }

    //회사 공고 목록
    @Transactional
    public List<JobOpeningDto> getJobOpeningList(Long companyId){
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        optionalCompany.orElseThrow(()->new NotFoundException("not found company"));

        if (optionalCompany.isPresent()){
            List<JobOpening> jobOpeningList = jobOpeningRepository.findByCompany(optionalCompany.get());
            List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(j->new JobOpeningDto(
                    j,
                    new CompanyDto(j.getCompany()),
                    GugunResponse.response(j.getGugun()),
                    JobChildCategoryResponse.response(j.getJobChildCategory())
            )).collect(Collectors.toList());

            return jobOpeningDtoList;
        }else {
            return null;
        }

    }

    //회사 공고 상세조회
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

    //회사 공고 수정
    @Transactional
    public JobOpeningDto updateJobOpening(Long jobOpeningId, JobOpeningDto jobOpeningDto){
        Gugun gugun = gugunRepository.findById(jobOpeningDto.getGugunId()).get();
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningDto.getJobChildCategoryId()).get();

        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found jobOpening"));

        if (optionalJobOpening.isPresent()){
            optionalJobOpening.get().update(jobOpeningDto,gugun,jobChildCategory);

            Optional<Company> optionalCompany = companyRepository.findById(1L);
            CompanyDto companyDto = new CompanyDto(optionalCompany.get());

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
    public void deleteJobOpening(Long jobOpeningId){

        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(()->new NotFoundException("not found JobOpening"));

        jobOpeningRepository.deleteById(jobOpeningId);
    }
    //회사 공고 지원자 목록
    //회사 공고 지원자 상세목록
    //회사 공고 지원자 상태수정



}

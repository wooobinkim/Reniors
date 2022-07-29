package com.common.jmark.service;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Eval;
import com.common.jmark.domain.entity.EvalQuestion;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.EvalQuestionRepository;
import com.common.jmark.domain.repository.EvalRepository;
import com.common.jmark.dto.CompanyDto;
import com.common.jmark.dto.EvalDto;
import com.common.jmark.dto.EvalQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvalService {

    private final EvalRepository evalRepository;
    private final EvalQuestionRepository evalQuestionRepository;
    private final CompanyRepository companyRepository;

    //평가 폼 등록
    @Transactional
    public EvalDto postEval(EvalDto evalDto){

        Optional<Company> optionalCompany = companyRepository.findById(evalDto.getCompanyId());
        if (optionalCompany.isPresent()){
            Eval eval = new Eval(evalDto, optionalCompany.get());
            evalRepository.save(eval);
            CompanyDto companyDto = new CompanyDto(optionalCompany.get());
            EvalDto evalDto1 = new EvalDto(eval,companyDto);
//            evalFormDto1.setLinkEntity(companyDto);
            return evalDto1;
        }else{
            return null;
        }
    }

    @Transactional
    //평가 폼 전체조회
    public Page<EvalDto> getEval(Long companyId, Pageable pageable){
        System.out.println("왜안옴");
        //토큰쓰기
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()){
            List<Eval> evalPage = evalRepository.findByCompany(optionalCompany.get());
            List<EvalDto> evalDtoList = new ArrayList<>();

            CompanyDto companyDto = new CompanyDto(optionalCompany.get());
            for (Eval eval : evalPage) {
                EvalDto evalDto = new EvalDto(eval, companyDto);
                evalDtoList.add(evalDto);
            }
            long total = evalDtoList.size();
            Page evalDtoPage = new PageImpl(evalDtoList,pageable,total);

            return evalDtoPage;

        }else{
            return null;
        }

    }


    //평가 폼 상세조회
    //평가 폼 수정
    //평가 폼 삭제

    //평가 질문 등록
    @Transactional
    public EvalQuestionDto postEvalQuestion(Long evalId, EvalQuestionDto evalQuestionDto){
        Optional<Eval> optionalEval = evalRepository.findById(evalId);

        if (optionalEval.isPresent()){
            EvalQuestion evalQuestion = new EvalQuestion(evalQuestionDto, optionalEval.get());

            evalQuestionRepository.save(evalQuestion);

            Optional<Company> optionalCompany = companyRepository.findById(optionalEval.get().getCompany().getId());
            CompanyDto companyDto = new CompanyDto(optionalCompany.get());
            EvalDto evalDto = new EvalDto(optionalEval.get(), companyDto);

            EvalQuestionDto evalQuestionDto1 = new EvalQuestionDto(evalQuestion, evalDto);
            return evalQuestionDto1;

        }else {
            return null;
        }
    }

    //평가 질문 조회
    //평가 질문 수정
    //평가 질문 삭제

    //유저 평가 입력
    //유저 평가 조회
    //유저 평가 삭제

}

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvalService {

    private final EvalRepository evalRepository;
    private final EvalQuestionRepository evalQuestionRepository;
    private final CompanyRepository companyRepository;

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

}

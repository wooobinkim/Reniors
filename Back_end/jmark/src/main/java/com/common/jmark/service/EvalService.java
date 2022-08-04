package com.common.jmark.service;

import com.common.jmark.common.exception.NotAuthException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Eval;
import com.common.jmark.domain.entity.EvalQuestion;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.EvalQuestionRepository;
import com.common.jmark.domain.repository.EvalRepository;
import com.common.jmark.dto.Eval.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotAuthException.COMPANY_NO_AUTH;


@Service
@RequiredArgsConstructor
public class EvalService {

    private final EvalRepository evalRepository;
    private final EvalQuestionRepository evalQuestionRepository;
    private final CompanyRepository companyRepository;

    //평가 폼 등록
    @Transactional
    public Long postEval(Company company, EvalCreateRequest evalCreateRequest){

        Eval eval = new Eval(evalCreateRequest, company);
        Long id = evalRepository.save(eval).getId();

        return id;

    }

    @Transactional
    //평가 폼 전체조회
    public Page<EvalResponse> getEvalList(Company company, Pageable pageable){
        //토큰쓰기
            List<Eval> evalPage = evalRepository.findByCompany(company);
            List<EvalResponse> evalResponseList = evalPage.stream().map(e->EvalResponse.response(
                    e
            )).collect(Collectors.toList());

            long total = evalResponseList.size();
            Page evalDtoPage = new PageImpl(evalResponseList,pageable,total);

            return evalDtoPage;

    }

    //평가 폼 상세조회
    @Transactional
    public EvalResponse getEval(Company company, Long evalId){
        if (company.getId() != evalRepository.findById(evalId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));

        EvalResponse evalResponse = EvalResponse.response(eval);

        return evalResponse;

    }

    //평가 폼 수정
    @Transactional
    public void updateEval(Company company, Long evalId, EvalUpdateRequest evalUpdateRequest){
        if (company.getId() != evalRepository.findById(evalId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));
        eval.update(evalUpdateRequest);
    }

    //평가 폼 삭제
    @Transactional
    public void deleteEval(Company company, Long evalId){
        if (company.getId() != evalRepository.findById(evalId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        evalRepository.deleteById(evalId);
    }

    //평가 질문 등록
    @Transactional
    public Long postEvalQuestion(Company company, Long evalId, EvalQuestionCreateRequest evalQuestionCreateRequest){
        if (company.getId() != evalRepository.findById(evalId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));


            EvalQuestion evalQuestion = new EvalQuestion(evalQuestionCreateRequest,eval);

        Long id = evalQuestionRepository.save(evalQuestion).getId();

        return id;

    }

    //평가 질문 조회
    @Transactional
    public List<EvalQuestionResponse> getEvalQuestionList(Company company, Long evalId){
        if (company.getId() != evalRepository.findById(evalId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));

        List<EvalQuestion> evalQuestionList = evalQuestionRepository.findByEval(eval);


        List<EvalQuestionResponse> evalQuestionResponses = evalQuestionList.stream().map(e->EvalQuestionResponse.response(e)
        ).collect(Collectors.toList());

        return evalQuestionResponses;

    }

    //평가 질문 상세조회
    @Transactional
    public EvalQuestionResponse getEvalQuestion(Company company, Long evalId, Long evalQuestionId){
        if (company.getId() != evalQuestionRepository.findById(evalQuestionId).get().getEval().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        EvalQuestion evalQuestion = evalQuestionRepository.findById(evalQuestionId).orElseThrow(() -> new NotFoundException("not found evalQuestion"));
        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));


        EvalQuestionResponse evalQuestionResponse = EvalQuestionResponse.response(evalQuestion);

        return evalQuestionResponse;

    }


    //평가 질문 수정
    @Transactional
    public void updateEvalQuestion(Company company, Long evalId, Long evalQuestionId, EvalQuestionUpdateRequest evalQuestionUpdateRequest){
        if (company.getId() != evalQuestionRepository.findById(evalQuestionId).get().getEval().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        EvalQuestion evalQuestion = evalQuestionRepository.findById(evalQuestionId).orElseThrow(() -> new NotFoundException("not found evalQuestion"));
        Eval eval = evalRepository.findById(evalId).orElseThrow(() -> new NotFoundException("not found eval"));

        evalQuestion.update(evalQuestionUpdateRequest);
    }

    //평가 질문 삭제
    @Transactional
    public void deleteEvalQuestion(Company company, Long evalQuestionId){
        if (company.getId() != evalQuestionRepository.findById(evalQuestionId).get().getEval().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        evalQuestionRepository.deleteById(evalQuestionId);
    }


    //유저 평가 입력
    //유저 평가 조회
    //유저 평가 삭제

}

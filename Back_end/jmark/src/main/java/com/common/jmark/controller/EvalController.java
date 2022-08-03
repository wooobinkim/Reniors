package com.common.jmark.controller;

import com.common.jmark.common.config.web.LoginCompany;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.dto.EvalDto;
import com.common.jmark.dto.EvalQuestionDto;
import com.common.jmark.service.EvalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eval")
@RequiredArgsConstructor
@Api(tags={"평가 API"})
public class EvalController {

    private final EvalService evalService;

    //평가 폼 등록
    @PostMapping
    @ApiOperation(value = "평가 폼 등록", notes = "평가 폼을 등록한다.")
    public ResponseEntity<?> postEval(@LoginCompany Company company, @RequestBody EvalDto evalDto){
        EvalDto Eval = evalService.postEval(company,evalDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Eval);
    }

    //평가 폼 전체조회
    @GetMapping
    @ApiOperation(value = "평가 폼 조회", notes = "평가 폼 전체를 조회한다.")
    public ResponseEntity<?> getEvalList(@LoginCompany Company company,Pageable pageable){
        Page<EvalDto> EvalList = evalService.getEvalList(company, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(EvalList);
    }

    //평가 폼 상세조회
    @GetMapping("/{evalId}")
    @ApiOperation(value = "평가 폼 상세조회", notes = "평가 폼 하나를 조회한다.")
    public ResponseEntity<?> getEval(@LoginCompany Company company,@PathVariable("evalId")Long evalId){
        EvalDto Eval = evalService.getEval(company, evalId);

        return ResponseEntity.status(HttpStatus.OK).body(Eval);
    }

    //평가 폼 수정
    @PutMapping("/{evalId}")
    @ApiOperation(value = "평가 폼 수정", notes = "평가 폼을 수정한다.")
    public ResponseEntity<?> updateEval(@LoginCompany Company company,
                                        @PathVariable("evalId")Long evalId,
                                        @RequestBody EvalDto evalDto){
        EvalDto Eval = evalService.updateEval(company, evalId, evalDto);

        return ResponseEntity.status(HttpStatus.OK).body(Eval);
    }

    //평가 폼 삭제
    @DeleteMapping("/{evalId}")
    @ApiOperation(value = "평가 폼 삭제", notes = "평가 폼을 삭제한다.")
    public ResponseEntity<?> deleteEval(@LoginCompany Company company,
                                        @PathVariable("evalId")Long evalId){
        evalService.deleteEval(company, evalId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //평가 질문 등록
    @PostMapping("/{evalId}/question")
    @ApiOperation(value = "평가 질문 등록", notes = "평가 폼에 대한 질문을 등록한다.")
    public ResponseEntity<?> postEvalQuestion(@LoginCompany Company company, @PathVariable("evalId") Long evalId, @RequestBody EvalQuestionDto evalQuestionDto){
        EvalQuestionDto EvalQuestion = evalService.postEvalQuestion(company, evalId, evalQuestionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(EvalQuestion);
    }

    //평가 질문 조회

    @GetMapping("/{evalId}/question")
    @ApiOperation(value = "평가 질문 조회", notes = "평가 폼에 대한 질문을 조회한다.")
    public ResponseEntity<?> getEvalQuestionList(@LoginCompany Company company,@PathVariable("evalId")Long evalId){
        List<EvalQuestionDto> EvalQuestionList = evalService.getEvalQuestionList(company,evalId);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestionList);
    }

    //평가 질문 상세조회
    @GetMapping("/{evalId}/question/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 상세조회", notes = "평가 폼에 대한 질문하나를 조회한다.")
    public ResponseEntity<?> getEvalQuestion(@LoginCompany Company company,
                                             @PathVariable("evalId")Long evalId,
                                             @PathVariable("evalQuestionId")Long evalQuestionId){
        EvalQuestionDto EvalQuestion = evalService.getEvalQuestion(company,evalId, evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 수정
    @PutMapping("/{evalId}/question/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 수정", notes = "평가 폼에 대한 질문 하나를 수정한다.")
    public ResponseEntity<?> updateEvalQuestion(@LoginCompany Company company,
                                                @PathVariable("evalId")Long evalId,
                                                @PathVariable("evalQuestionId")Long evalQuestionId,
                                                @RequestBody EvalQuestionDto evalQuestionDto){
        EvalQuestionDto EvalQuestion = evalService.updateEvalQuestion(company,evalId, evalQuestionId, evalQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 삭제
    @DeleteMapping("/{evalId}/question/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 삭제", notes = "평가 폼에 대한 질문 하나를 삭제한다.")
    public ResponseEntity<?> deleteEvalQuestion(@LoginCompany Company company,@PathVariable("evalQuestionId")Long evalQuestionId){
        evalService.deleteEvalQuestion(company,evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //유저 평가 입력
    //유저 평가 조회
    //유저 평가 삭제


}

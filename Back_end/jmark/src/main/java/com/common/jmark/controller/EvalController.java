package com.common.jmark.controller;

import com.common.jmark.dto.EvalDto;
import com.common.jmark.dto.EvalQuestionDto;
import com.common.jmark.service.EvalService;
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
public class EvalController {

    private final EvalService evalService;

    //평가 폼 등록
    @PostMapping
    public ResponseEntity<?> postEval(@RequestBody EvalDto evalDto){
        EvalDto Eval = evalService.postEval(evalDto);

        return ResponseEntity.status(HttpStatus.OK).body(Eval);
    }

    //평가 폼 전체조회
    @GetMapping
    public ResponseEntity<?> getEvalList(Pageable pageable){
        Page<EvalDto> EvalList = evalService.getEvalList(1L, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(EvalList);
    }

    //평가 폼 상세조회
    @GetMapping("/{evalId}")
    public ResponseEntity<?> getEval(@PathVariable("evalId")Long evalId){
        EvalDto Eval = evalService.getEval(evalId);

        return ResponseEntity.status(HttpStatus.OK).body(Eval);
    }

    //평가 폼 수정
    @PutMapping("/{evalId}")
    public ResponseEntity<?> updateEval(@PathVariable("evalId")Long evalId, @RequestBody EvalDto evalDto){
        EvalDto Eval = evalService.updateEval(evalId, evalDto);

        return ResponseEntity.status(HttpStatus.OK).body(Eval);
    }

    //평가 폼 삭제
    @DeleteMapping("/{evalId}")
    public ResponseEntity<?> deleteEval(@PathVariable("evalId")Long evalId){
        System.out.println("evalId = " + evalId);
        evalService.deleteEval(evalId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //평가 질문 등록
    @PostMapping("/{evalId}/question")
    public ResponseEntity<?> postEvalQuestion(@PathVariable("evalId") Long evalId, @RequestBody EvalQuestionDto evalQuestionDto){
        EvalQuestionDto EvalQuestion = evalService.postEvalQuestion(evalId, evalQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 조회

    @GetMapping("/{evalId}/question")
    public ResponseEntity<?> getEvalQuestionList(@PathVariable("evalId")Long evalId){
        List<EvalQuestionDto> EvalQuestionList = evalService.getEvalQuestionList(evalId);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestionList);
    }

    //평가 질문 상세조회
    @GetMapping("/{evalId}/question/{evalQuestionId}")
    public ResponseEntity<?> getEvalQuestion(@PathVariable("evalId")Long evalId, @PathVariable("evalQuestionId")Long evalQuestionId){
        EvalQuestionDto EvalQuestion = evalService.getEvalQuestion(evalId, evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 수정
    @PutMapping("/{evalId}/question/{evalQuestionId}")
    public ResponseEntity<?> updateEvalQuestion(@PathVariable("evalId")Long evalId,
                                                @PathVariable("evalQuestionId")Long evalQuestionId,
                                                @RequestBody EvalQuestionDto evalQuestionDto){
        EvalQuestionDto EvalQuestion = evalService.updateEvalQuestion(evalId, evalQuestionId, evalQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 삭제
    @DeleteMapping("/{evalId}/question/{evalQuestionId}")
    public ResponseEntity<?> deleteEvalQuestion(@PathVariable("evalQuestionId")Long evalQuestionId){
        evalService.deleteEvalQuestion(evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //유저 평가 입력
    //유저 평가 조회
    //유저 평가 삭제


}

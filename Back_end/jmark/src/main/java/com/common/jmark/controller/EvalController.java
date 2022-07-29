package com.common.jmark.controller;

import com.common.jmark.dto.EvalDto;
import com.common.jmark.dto.EvalQuestionDto;
import com.common.jmark.service.EvalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eval")
@RequiredArgsConstructor
public class EvalController {

    private final EvalService evalService;

    //평가 폼 등록
    @PostMapping
    public ResponseEntity<?> postEval(@RequestBody EvalDto evalDto){
        EvalDto EvalForm = evalService.postEval(evalDto);

        return ResponseEntity.status(HttpStatus.OK).body(EvalForm);
    }

    //평가 폼 전체조회
    //평가 폼 상세조회
    //평가 폼 수정
    //평가 폼 삭제

    //평가 질문 등록
    @PostMapping("/{evalId}/question")
    public ResponseEntity<?> postEvalQuestion(@PathVariable("evalId") Long evalId, @RequestBody EvalQuestionDto evalQuestionDto){
        EvalQuestionDto EvalQuestion = evalService.postEvalQuestion(evalId, evalQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(EvalQuestion);
    }

    //평가 질문 조회
    //평가 질문 수정
    //평가 질문 삭제

    //유저 평가 입력
    //유저 평가 조회
    //유저 평가 삭제제

}

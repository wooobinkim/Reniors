package com.common.jmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jobopening")
public class TestController {

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }

    //공고 전체 조회
    //공고 한건 조회
    @GetMapping("/{job_opening_id}")
    public ResponseEntity<?> getJobOpeningById(@PathVariable("job_opening_id") Long id, HttpServletRequest request){

        return null;
    }

    //공고 등록
    @PostMapping()
    public ResponseEntity<?> addJobOpening(@RequestBody )

    //공고 수정
    //공고 삭제

}

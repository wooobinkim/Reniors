package com.common.jmark.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String test(){
        return "<h1>서버 배포 테스트</h1>";
    }

}

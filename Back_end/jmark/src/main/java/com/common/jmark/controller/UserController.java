package com.common.jmark.controller;

import com.common.jmark.common.config.web.LoginUser;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request){
        String accessToken = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .build();
    }

    @PostMapping("/regist")
    public  ResponseEntity<?> regist(@Valid @RequestBody UserCreateRequest request){
        Long userId = userService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> getUserInfo(@LoginUser User user){
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

}

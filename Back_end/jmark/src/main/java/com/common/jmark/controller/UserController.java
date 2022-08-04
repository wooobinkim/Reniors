package com.common.jmark.controller;

import com.common.jmark.common.config.web.LoginUser;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {"회원 API"})
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "자체 서비스 로그인", notes = "아이디와 비밀번호를 입력하여 로그인합니다.")
    public ResponseEntity<?> loginUser (
            @Valid @RequestBody UserLoginRequest request
    ) {
        String accessToken = userService.loginUser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .build();
    }

    @PostMapping("/regist")
    @ApiOperation(value = "자체 서비스 회원가입", notes = "회원가입에 필요한 정보를 입력하고 회원으로 가입합니다.")
    public ResponseEntity<?> registUser (
            @Valid @RequestBody UserCreateRequest request
    ) {
        Long userId = userService.createUser(request);
        Map<String, Long> response = new HashMap<>();
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "유저 상세 정보 조회", notes = "유저의 상세 정보를 조회합니다.")
    public ResponseEntity<?> readUser (
            @ApiIgnore @LoginUser User user
    ) {
        return ResponseEntity.ok(userService.readUser(user));
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "유저의 정보를 삭제하고 회원을 탈퇴합니다.")
    public ResponseEntity<Map<String, Long>> deleteUser(

    ) {
        return null;
    }
}

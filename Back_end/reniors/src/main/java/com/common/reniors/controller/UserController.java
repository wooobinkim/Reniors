package com.common.reniors.controller;

import com.common.reniors.common.config.data.service.AwsS3Service;
import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.kakao.KakaoUserInfo;
import com.common.reniors.dto.mail.MailDto;
import com.common.reniors.dto.user.UserCreateRequest;
import com.common.reniors.dto.user.UserLoginRequest;
import com.common.reniors.dto.user.UserUpdateRequest;
import com.common.reniors.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {"회원 API"})
public class UserController {
    private static final String baseURL = "https://reniors.s3.ap-northeast-2.amazonaws.com/";
    private final UserService userService;
    private final AwsS3Service awsS3Service;

    // 자체 서비스 로그인
    @PostMapping("/login")
    @ApiOperation(value = "자체 서비스 로그인", notes = "아이디와 비밀번호를 입력하여 로그인합니다.")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody UserLoginRequest request
    ) {
        String accessToken = userService.loginUser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .build();
    }

    // 아이디 중복 검사 로직
    @GetMapping(path="/idCheck/{userAppId}")
    @ApiOperation(value = "유저 아아디 중복 검사", notes = "아이디 중복 검사를 진행합니다.")
    public ResponseEntity<?> idCheck(
            @PathVariable String userAppId
    ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("res", userService.idCheck(userAppId));
        return ResponseEntity.ok(response);
    }

    // 자체 서비스 회원가입
    @PostMapping(path="/regist",consumes = {"multipart/form-data"})
    @ApiOperation(value = "자체 서비스 회원가입", notes = "회원가입에 필요한 정보를 입력하고 회원으로 가입합니다.")
    public ResponseEntity<?> registUser(
            @RequestPart(value = "img", required = false) final MultipartFile file,
            @Valid @RequestPart(value = "data", required = true) final UserCreateRequest request
    ) throws Exception {
        // TODO : URL 추가
        String userProfile = "userBaseProfile.png";
        if(file != null) {
            userProfile = awsS3Service.uploadFile(file, "user/");
        }
        Long userId = userService.createUser(request, baseURL, "user/"+userProfile);
        Map<String, Long> response = new HashMap<>();
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }

    // 카카오 회원가입/로그인
    @GetMapping("/login/kakao")
    @ApiOperation(value = "카카오 로그인/회원가입", notes = "카카오 계정으로 로그인/회원가입을 합니다.")
    public ResponseEntity<?> kakaoLogin(
            @RequestParam String code,
            HttpServletResponse response
    ) throws JsonProcessingException {
        String accessToken = userService.kakaoLogin(code, response, baseURL);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .build();
    }

    // 카카오 회원 탈퇴

    // 회원 로그아웃

    // 카카오 로그아웃

    // 유저 상세 정보 조회
    @GetMapping
    @ApiOperation(value = "유저 상세 정보 조회", notes = "유저의 상세 정보를 조회합니다.")
    public ResponseEntity<?> readUser(
            @ApiIgnore @LoginUser User user
    ) {
        return ResponseEntity.ok(userService.readUser(user));
    }

    // 유저 목록 조회
    @GetMapping("/list")
    @ApiOperation(value = "유저 목록 조회", notes = "유저 목록을 조회합니다.")
    public ResponseEntity<?> readUserList() {
        return ResponseEntity.ok(userService.readUserList());
    }

    // 회원 정보 수정
    @PutMapping
    @ApiOperation(value = "회원 정보 수정", notes = "유저의 정보를 수정합니다.")
    // TODO : image 수정 추가
    public ResponseEntity<?> updateUser(
            @ApiIgnore @LoginUser User user,
            @RequestPart(value = "img", required = false) final MultipartFile file,
            @Valid @RequestPart(value = "data", required = true) final UserUpdateRequest request
    ) throws Exception {
        String userProfile = "userBaseProfile.png";
        // 프로필사진을 바꿀 것인지 확인
        if(file != null && request.isChangeProfile()) {
            userProfile = awsS3Service.uploadFile(file, "user/");
        }
        userService.updateUser(user.getId(), request, baseURL+"user/", userProfile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 탈퇴
    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "유저의 정보를 삭제하고 회원을 탈퇴합니다.")
    public ResponseEntity<Map<String, Long>> deleteUser(
            @ApiIgnore @LoginUser User user
    ) {
        awsS3Service.deleteFile(user.getUserProfile());
        userService.deleteUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 아이디 찾기
    @GetMapping("/{name}/findId/{phone}")
    @ApiOperation(value = "아이디 찾기", notes = "유저의 이름과 전화번호를 통해 아이디를 찾습니다.")
    public ResponseEntity<?> findId(
            @PathVariable String name,
            @PathVariable String phone
    ) {
        return ResponseEntity.ok(userService.findIdByPhone(name, phone));
    }

    // 비밀번호 찾기
    @GetMapping("/{name}/findPwd/{userAppId}")
    @ApiOperation(value = "비밀번호 찾기", notes = "유저의 이름과 서비스 아이디를 통해 비밀번호를 찾습니다.")
    public ResponseEntity<?> findPwd(
            @PathVariable String name,
            @PathVariable String userAppId
    ) {
        User user = userService.findByNameAndUserAppId(name, userAppId);
        if (user.getUserAppId() != null && !user.getUserAppId().equals("")) {
            MailDto mailDto = userService.createMailAndChangePwd(user.getUserAppId());
            userService.mailSend(mailDto);
        } else {
            return ResponseEntity.ok("이메일 형식이 올바르지 않습니다.");
        }
        return ResponseEntity.ok("임시 비밀번호가 이메일로 전송되었습니다.");
    }

    // 비밀번호 변경
    @PutMapping("/{userAppPwd}")
    @ApiOperation(value = "회원 비밀번호 수정", notes = "유저의 비밀번호를 수정합니다.")
    public ResponseEntity<?> updateUserPwd(
            @ApiIgnore @LoginUser User user,
            @PathVariable String userAppPwd
    ) {
        userService.updateUserPwd(user.getUserAppId(), userAppPwd);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

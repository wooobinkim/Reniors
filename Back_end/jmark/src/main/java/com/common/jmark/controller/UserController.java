package com.common.jmark.controller;

import com.common.jmark.common.config.data.service.AwsS3Service;
import com.common.jmark.common.config.web.LoginUser;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserUpdateRequest;
import com.common.jmark.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {"회원 API"})
public class UserController {

    private static final String baseURL = "https://reniors.s3.ap-northeast-2.amazonaws.com/";

    private final UserService userService;
    private final AwsS3Service awsS3Service;

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

    @PostMapping(path="/regist",consumes = {"multipart/form-data"})
    @ApiOperation(value = "자체 서비스 회원가입", notes = "회원가입에 필요한 정보를 입력하고 회원으로 가입합니다.")
    public ResponseEntity<?> registUser(
            @RequestPart(value = "img", required = false) final MultipartFile file,
            @Valid @RequestPart(value = "data", required = true) final UserCreateRequest request
    ) throws Exception {
        // TODO : URL 추가
        String userProfile = "user/userBaseProfile.png";
        if(file != null) {
            userProfile = awsS3Service.uploadFile(file, "user/");
        }
        Long userId = userService.createUser(request, baseURL, userProfile);
        Map<String, Long> response = new HashMap<>();
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }

    // 카카오 회원가입

    // 카카오 로그인

    @GetMapping
    @ApiOperation(value = "유저 상세 정보 조회", notes = "유저의 상세 정보를 조회합니다.")
    public ResponseEntity<?> readUser(
            @ApiIgnore @LoginUser User user
    ) {
        return ResponseEntity.ok(userService.readUser(user));
    }

    @GetMapping("/list")
    @ApiOperation(value = "유저 목록 조회", notes = "유저 목록을 조회합니다.")
    public ResponseEntity<?> readUserList() {
        return ResponseEntity.ok(userService.readUserList());
    }

    @PutMapping
    @ApiOperation(value = "회원 정보 수정", notes = "유저의 정보를 수정합니다.")
    // TODO : image 수정 추가
    public ResponseEntity<?> updateUser(
            @ApiIgnore @LoginUser User user,
            @RequestPart(value = "img", required = false) MultipartFile file,
            @Valid @RequestPart UserUpdateRequest request
    ) throws Exception {
        // TODO : URL 추가
        String userProfile = "user/userBaseProfile.png";
        // 프로필사진을 바꿀 것인지 확인
        if(file != null && request.isChangeProfile()) {
            userProfile = awsS3Service.uploadFile(file, "user/");
        }
        userService.updateUser(user.getId(), request, baseURL, userProfile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

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
        return ResponseEntity.ok(userService.findPwdByUserAppId(name, userAppId));
    }
}

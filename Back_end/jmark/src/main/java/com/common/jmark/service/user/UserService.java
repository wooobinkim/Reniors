package com.common.jmark.service.user;

import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserResponse;
import com.common.jmark.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {
    String loginUser(UserLoginRequest request);

    // 카카오 회원가입

    // 카카오 로그인

    Long createUser(UserCreateRequest request);

    UserResponse readUser(User user);

    List<UserResponse> readUserList();

    void updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);

    // 아이디 찾기
    String findIdByPhone(String name, String phone);

    // 비밀번호 찾기
    String findPwdByUserAppId(String name, String userAppId);

    User validateUser(User user);
}

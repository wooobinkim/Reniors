package com.common.jmark.service.user;

import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserResponse;
import com.common.jmark.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {
    String loginUser(UserLoginRequest request);

    // 아이디 찾기

    // 비밀번호 찾기

    Long createUser(UserCreateRequest request);

    UserResponse readUser(User user);

    void updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);

    List<User> readUserList();

    User validateUser(User user);


}

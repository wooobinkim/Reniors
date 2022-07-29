package com.common.jmark.service.user;

import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserResponse;

public interface UserService {
    public Long create(UserCreateRequest request);
    public UserResponse login(UserLoginRequest request);
}

package com.common.jmark.service.user;

import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.mail.MailDto;
import com.common.jmark.dto.user.*;

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

    // 이름과 아이디로 회원 찾기
    User findByNameAndUserAppId(String name, String userAppId);

    // 아이디 찾기
    String findIdByPhone(String name, String phone);

    // 메일을 생성하고 임시 비밀번호로 회원의 비밀번호를 변경
    MailDto createMailAndChangePwd(String userAppId);

    // 랜덤 함수로 임시 비밀번호 생성
    String getNewPwd();

    // 메일 전송
    void mailSend(MailDto mailDto);

    // 비밀번호 변경
    void updateUserPwd(String userAppId, String newPwd);

    User validateUser(User user);
}

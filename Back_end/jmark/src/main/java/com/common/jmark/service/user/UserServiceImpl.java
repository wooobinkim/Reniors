package com.common.jmark.service.user;

import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.common.exception.NotMatchException;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserResponse;
import com.common.jmark.dto.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.common.jmark.common.exception.NotFoundException.USER_LIST_NOT_FOUND;
import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;
import static com.common.jmark.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public String loginUser(UserLoginRequest request) {
        Optional<User> findUser = userRepository.findByUserAppId(request.getUserAppId());
        // 해당 아이디를 가진 유저가 존재하지 않을 때
        if(!findUser.isPresent()){
            throw new NotFoundException(USER_NOT_FOUND);
        }else{
            //비밀번호 확인
            if (passwordEncoder.matches(request.getUserAppPwd(), findUser.get().getUserAppPwd())){
                return jwtUtil.createToken(findUser.get().getId(), "user");
            }
            else {
                throw new NotMatchException(PASSWORD_NOT_MATCH);
            }
        }
    }

    @Override
    @Transactional
    public Long createUser(UserCreateRequest request) {
        if(userRepository.findByPhone(request.getPhone()).isPresent()){
            // 카카오로 회원가입 되있을 시 생각
            throw new DuplicateException(String.format("%s는 이미 가입된 회원입니다.",request.getUserAppId()));
        }else {
            User saveUser = User.create(
                    request.getUserAppId(),
                    passwordEncoder.encode(request.getUserAppPwd()),
                    request.getKakaoId(),
                    request.getName(),
                    request.getBirth(),
                    request.getGender(),
                    request.getPhone(),
                    request.getTotalCareer(),
                    request.getProfileImgName(),
                    request.getProfileImgPath(),
                    request.getAddress(),
                    request.getIsOpen(),
                    request.getLastEdu(),
                    request.getPortfolioName(),
                    request.getPortfolioPath()
            );
            return userRepository.save(saveUser).getId();
        }
    }

    @Override
    @Transactional
    public UserResponse readUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserResponse.response(findUser);
    }

    @Override
    @Transactional
    public List<UserResponse> readUserList() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty() || users == null) {
            throw new NotFoundException(USER_LIST_NOT_FOUND);
        }
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(UserResponse.response(user));
        }
        return userResponses;
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.update(
                passwordEncoder.encode(request.getUserAppPwd()),
                request.getName(),
                request.getBirth(),
                request.getGender(),
                request.getPhone(),
                request.getTotalCareer(),
                request.getProfileImgName(),
                request.getProfileImgPath(),
                request.getAddress(),
                request.getIsOpen(),
                request.getLastEdu(),
                request.getPortfolioName(),
                request.getPortfolioPath()
        );
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public String findIdByPhone(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return user.getUserAppId();
    }

    @Override
    @Transactional
    public String findPwdByUserAppId(String name, String userAppId) {
        User user = userRepository.findByNameAndUserAppId(name, userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return user.getUserAppPwd();
    }

    @Override
    @Transactional
    public User validateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return findUser;
    }
}

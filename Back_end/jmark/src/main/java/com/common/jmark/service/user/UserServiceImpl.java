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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;
import static com.common.jmark.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public String loginUser(UserLoginRequest request) {
        Optional<User> findUser = userRepository.findByUserAppId(request.getUserAppId());
        // 해당 아이디를 가진 유저가 존재하지 않을 때
        if(!findUser.isPresent()){
            throw new NotFoundException(USER_NOT_FOUND);
        }else{
            //비밀번호 확인
            if(findUser.get().getUserAppPwd().equals(request.getUserAppPwd())){
                return jwtUtil.createToken(findUser.get().getId());
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
            throw new DuplicateException(String.format("%s는 이미 존재하는 회원입니다.",request.getUserAppId()));
        }else {
            User saveUser = User.create(
                    request.getUserAppId(),
                    request.getUserAppPwd(),
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
    public void updateUser(Long userId, UserUpdateRequest request) {

    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {

    }

    @Override
    @Transactional
    public List<User> readUserList() {
        return null;
    }

    @Override
    @Transactional
    public User validateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return findUser;
    }
}

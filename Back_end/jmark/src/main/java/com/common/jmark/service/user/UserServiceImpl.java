package com.common.jmark.service.user;

import com.common.exception.DuplicateException;
import com.common.exception.NotFoundException;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.user.UserCreateRequest;
import com.common.jmark.dto.user.UserLoginRequest;
import com.common.jmark.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long create(UserCreateRequest request) {
        if(userRepository.findByPhone(request.getPhone()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 회원입니다.",request.getName()));
        }
        User user = User.create(
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
                request.getWorkingDay(),
                request.getMinSalary(),
                request.getPortfolioName(),
                request.getPortfolioPath()
        );
        return userRepository.save(user).getId();
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
         User user = userRepository.findByUserAppId(request.getUserAppId())
                 .orElseThrow(()->new NotFoundException("Not Found User"));
         return UserResponse.response(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

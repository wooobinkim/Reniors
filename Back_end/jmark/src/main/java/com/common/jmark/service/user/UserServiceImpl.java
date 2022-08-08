package com.common.jmark.service.user;

import com.common.jmark.common.config.security.util.JwtUtil;
import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.common.exception.NotMatchException;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.mail.MailDto;
import com.common.jmark.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

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
    public User findByNameAndUserAppId(String name, String userAppId) {
        User user = userRepository.findByNameAndUserAppId(name, userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return user;
    }

    @Override
    @Transactional
    public MailDto createMailAndChangePwd(String userAppId) {
        User user = userRepository.findByUserAppId(userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        String newPwd = getNewPwd();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(user.getUserAppId());
        mailDto.setTitle("Reniors 임시 비밀번호 안내 이메일입니다.");
        mailDto.setMessage("안녕하세요. Reniors 임시 비밀번호 안내 관련 이메일입니다." + " 회원님의 임시 비밀번호는 "
                            + newPwd + " 입니다." + " 로그인 후에 비밀번호를 변경해주세요.");
        // 임시 비밀번호로 회원의 비밀번호를 변경
        user.updatePwd(passwordEncoder.encode(newPwd));
        return mailDto;
    }

    @Override
    @Transactional
    public String getNewPwd() {
        char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String pwd = "";
        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charArr.length * Math.random());
            pwd += charArr[idx];
        }
        return pwd;
    }

    @Override
    @Transactional
    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(from);
        message.setReplyTo(from);
        mailSender.send(message);
    }

    @Override
    @Transactional
    public void updateUserPwd(String userAppId, String newPwd) {
        User user = userRepository.findByUserAppId(userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.updatePwd(passwordEncoder.encode(newPwd));
    }

    @Override
    @Transactional
    public User validateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return findUser;
    }
}

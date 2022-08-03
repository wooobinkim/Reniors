package com.common.jmark.common.config.security.service;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("AuthService - loadUserByUsername");

        StringTokenizer st = new StringTokenizer(userId,",");
        String id = st.nextToken();
        String name = st.nextToken();
        System.out.println("id = " + id);
        System.out.println("name = " + name);

        if (name.equals("user")) {
            User findUser = userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

            System.out.println("findUser = " + findUser);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(String.valueOf(findUser.getRole())));

            System.out.println("authorities = " + authorities);

            LoginUserDetails loginUserDetails = new LoginUserDetails(findUser.getName(), "", authorities);
            loginUserDetails.setUser(findUser);

            System.out.println("loginUserDetails = " + loginUserDetails);

            return loginUserDetails;
        }else{
            Company findCompany = companyRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            LoginCompanyDetails loginCompanyDetails = new LoginCompanyDetails(findCompany.getName(),"",authorities);
            loginCompanyDetails.setCompany(findCompany);

            return loginCompanyDetails;

        }

    }


}


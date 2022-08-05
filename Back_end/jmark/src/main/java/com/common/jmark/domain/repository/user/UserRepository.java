package com.common.jmark.domain.repository.user;

import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserAppId(String userAppID);
    Optional<User> findByPhone(String phone);
    Optional<User> findById(Long Id);
    Optional<User> findByNameAndPhone(String name, String phone);
    Optional<User> findByNameAndUserAppId(String name, String phone);
}

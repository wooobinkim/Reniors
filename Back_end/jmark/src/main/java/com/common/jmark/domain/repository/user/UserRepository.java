package com.common.jmark.domain.repository.user;

import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserAppId(String userAppID);
    Optional<User> findByPhone(String phone);
}

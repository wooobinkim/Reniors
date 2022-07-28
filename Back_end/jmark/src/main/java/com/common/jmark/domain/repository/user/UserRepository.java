package com.common.jmark.domain.repository.user;

import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

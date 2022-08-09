package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.UserEval;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEvalRepository extends JpaRepository<UserEval, Long> {
    List<UserEval> findByUser(User user);
}

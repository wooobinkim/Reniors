package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Apply;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findByJobOpening(JobOpening jobOpening);
    List<Apply> findByUser(User user);
}

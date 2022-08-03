package com.common.jmark.domain.repository.resume;

import com.common.jmark.domain.entity.resume.CareerDetail;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerDetailRepository extends JpaRepository<CareerDetail, Long> {
    List<CareerDetail> findByUser(User user);
}

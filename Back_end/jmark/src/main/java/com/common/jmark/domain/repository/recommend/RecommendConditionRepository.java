package com.common.jmark.domain.repository.recommend;

import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendConditionRepository extends JpaRepository<RecommendCondition, Long> {
    Optional<RecommendCondition> findByUser(User user);
}

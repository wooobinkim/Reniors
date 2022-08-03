package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.SearchCondition;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.SearchConditionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchConditionRepository extends JpaRepository<SearchCondition, Long> {
    List<SearchCondition> findByUser(User user);
}

package com.common.reniors.domain.repository.recoding;

import com.common.reniors.domain.entity.recoding.Recoding;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecodingRepository extends JpaRepository<Recoding, Long> {
    List<Recoding> findByUser( User user );
}

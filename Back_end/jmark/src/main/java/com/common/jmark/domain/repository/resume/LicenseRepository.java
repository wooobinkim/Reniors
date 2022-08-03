package com.common.jmark.domain.repository.resume;

import com.common.jmark.domain.entity.resume.License;
import com.common.jmark.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByUser(User user);
}

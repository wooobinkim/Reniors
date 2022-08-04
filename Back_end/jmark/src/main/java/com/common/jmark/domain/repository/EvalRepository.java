package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Eval;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvalRepository extends JpaRepository<Eval,Long> {

    List<Eval> findByCompany(Company company);

}

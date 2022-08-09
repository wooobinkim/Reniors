package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.Eval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvalRepository extends JpaRepository<Eval,Long> {

    List<Eval> findByCompany(Company company);

}

package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Eval;
import com.common.jmark.domain.entity.EvalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvalQuestionRepository extends JpaRepository<EvalQuestion, Long> {

    List<EvalQuestion> findByEval(Eval eval);
}

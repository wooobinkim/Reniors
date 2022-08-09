package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Eval;
import com.common.reniors.domain.entity.EvalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvalQuestionRepository extends JpaRepository<EvalQuestion, Long> {

    List<EvalQuestion> findByEval(Eval eval);

}

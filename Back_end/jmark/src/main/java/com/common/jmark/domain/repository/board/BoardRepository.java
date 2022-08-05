package com.common.jmark.domain.repository.board;

import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.category.JobParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(JobParentCategory jpc);
}

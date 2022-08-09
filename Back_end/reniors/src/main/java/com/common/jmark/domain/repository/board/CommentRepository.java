package com.common.jmark.domain.repository.board;

import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}

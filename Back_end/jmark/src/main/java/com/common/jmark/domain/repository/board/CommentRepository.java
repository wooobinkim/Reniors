package com.common.jmark.domain.repository.board;

import com.common.jmark.domain.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

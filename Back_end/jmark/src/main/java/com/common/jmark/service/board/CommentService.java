package com.common.jmark.service.board;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.board.Comment;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.board.BoardRepository;
import com.common.jmark.domain.repository.board.CommentRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.board.CommentCreateRequest;
import com.common.jmark.dto.board.CommentResponse;
import com.common.jmark.dto.board.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long create(Long boardId, CommentCreateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        User findUser = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
        Comment comment = Comment.create(request.getContents(), board, findUser);
        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void update(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new NotFoundException(COMMENT_NOT_FOUND));
        comment.update(request.getContents());
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new NotFoundException(COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }

    @Transactional
    public List<CommentResponse> getCommentList(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        List<CommentResponse> comments = commentRepository.findByBoard(board).stream()
                .map(CommentResponse::response)
                .collect(Collectors.toList());
        return comments;
    }
}

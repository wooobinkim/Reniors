package com.common.jmark.controller;

import com.common.jmark.dto.board.*;
import com.common.jmark.service.board.BoardService;
import com.common.jmark.service.board.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Api(tags={"게시판 API"})
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    public ResponseEntity<?> createBoard(
            @Valid @RequestBody BoardCreateRequest request
            ){
        Long boardId = boardService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("boardId", boardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")
    public ResponseEntity<?> getBoardList(
            @Valid @RequestBody BoardSearchRequest request,
            Pageable pageable
            ){
        // TODO : URI 생각하고 작성하기
        return ResponseEntity.ok(boardService.getBoardList(request, pageable));
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 상세조회", notes = "게시글을 상세조회한다.")
    public ResponseEntity<?> getBoardInfo(
            @PathVariable Long boardId
    ){
        return ResponseEntity.ok(boardService.getBoardInfo(boardId));
    }

    @PutMapping("/{boardId}")
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    public ResponseEntity<?> updateBoard(
            @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequest request
            ){
        boardService.update(boardId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    public ResponseEntity<?> deleteBoard(
            @PathVariable Long boardId
    ){
        boardService.delete(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{boardId}/comments")
    @ApiOperation(value = "댓글 생성", notes = "해당 게시글에 댓글을 생성한다.")
    public ResponseEntity<?> createComment(
            @PathVariable Long boardId,
            @Valid @RequestBody CommentCreateRequest request
    ){
        Long commentId = commentService.create(boardId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("commentId", commentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}/comments")
    @ApiOperation(value = "댓글 조회", notes = "해당 게시글의 댓글을 조회한다.")
    public ResponseEntity<?> getCommentList(
            @PathVariable("boardId") Long boardId
            ){
        return ResponseEntity.ok(commentService.getCommentList(boardId));
    }

    @PutMapping("/{boardId}/comments/{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "해당 게시글의 댓글을 수정한다.")
    public ResponseEntity<?> updateComment(
        @PathVariable("boardId") Long boardId,
        @PathVariable("commentId") Long commentId,
        @Valid @RequestBody CommentUpdateRequest request
    ){
        commentService.update(commentId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "해당 게시글의 댓글을 삭제한다.")
    public ResponseEntity<?> deleteComment(
        @PathVariable("boardId") Long boardId,
        @PathVariable("commentId") Long commentId
    ){
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

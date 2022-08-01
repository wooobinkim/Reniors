package com.common.jmark.controller;

import com.common.jmark.dto.board.BoardCreateRequest;
import com.common.jmark.service.board.BoardService;
import com.common.jmark.service.board.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags={"게시판 API"})
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createBoard(
            @Valid @RequestBody BoardCreateRequest request
            ){
        Long boardId = boardService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("boardId", boardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getBoardList(
    ){
        // TODO : URI 생각하고 작성하기
        return ResponseEntity.ok(boardService.getBoardList(1L));
    }
}

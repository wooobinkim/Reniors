package com.common.jmark.service.board;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.category.JobParentCategory;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.board.BoardRepository;
import com.common.jmark.domain.repository.category.JobParentCategoryRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.board.BoardCreateRequest;
import com.common.jmark.dto.board.BoardResponse;
import com.common.jmark.dto.board.BoardUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(BoardCreateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        User findUser = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
        Board board = Board.create(request.getTitle(), request.getContents(), findUser, jpc);
        return boardRepository.save(board).getId();
    }

    @Transactional
    public void update(Long boardId, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        board.update(request.getTitle(), request.getContents());
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        boardRepository.delete(board);
    }

    @Transactional
    public List<BoardResponse> getBoardList(Long categoryId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(categoryId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        List<BoardResponse> boards = boardRepository.findByCategory(jpc).stream()
                .map(BoardResponse::response)
                .collect(Collectors.toList());
        return boards;
    }

    @Transactional
    public BoardResponse getBoardInfo(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        return BoardResponse.response(board);
    }
}

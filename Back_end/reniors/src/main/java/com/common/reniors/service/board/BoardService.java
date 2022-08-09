package com.common.reniors.service.board;

import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.QBoard;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.board.BoardRepository;
import com.common.reniors.domain.repository.category.JobParentCategoryRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.board.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Long create(BoardCreateRequest request,User user) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Board board = Board.create(request.getTitle(), request.getContents(), user, jpc);
        return boardRepository.save(board).getId();
    }

    @Transactional
    public void update(Long boardId, BoardUpdateRequest request, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        if(board.getUser().getId() == user.getId()) {
            board.update(request.getTitle(), request.getContents());
        }
        else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public void delete(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        if(board.getUser().getId() == user.getId()) {
            boardRepository.delete(board);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public List<BoardResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QBoard b = new QBoard("b");
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JobParentCategory jpc = jobParentCategoryRepository.findById(boardSearchRequest.getCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));

        if(boardSearchRequest.getName() != null) booleanBuilder.and(b.user.name.contains(boardSearchRequest.getName()));
        if(boardSearchRequest.getTitle() != null) booleanBuilder.and(b.title.contains(boardSearchRequest.getTitle()));
        if(boardSearchRequest.getBoardId() != null) booleanBuilder.and(b.id.eq(boardSearchRequest.getBoardId()));

        List<Board> boardList = jpaQueryFactory.selectFrom(b)
                .where(
                        booleanBuilder,
                        b.category.id.eq(boardSearchRequest.getCategoryId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if(boardList.size() != 0){
            List<BoardResponse> boards = boardList.stream()
                    .map(BoardResponse::response)
                    .collect(Collectors.toList());
            return boards;
        }
        return null;
    }

    @Transactional
    public BoardDetailResponse getBoardInfo(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        board.viewsUp();
        return BoardDetailResponse.response(board);
    }
}
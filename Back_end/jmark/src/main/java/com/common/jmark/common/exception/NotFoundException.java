package com.common.jmark.common.exception;

public class NotFoundException extends RuntimeException {

    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리입니다.";
    public static final String BOARD_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String COMMENT_NOT_FOUND = "존재하지 않는 댓글입니다.";

    public NotFoundException(String message) {
        super(message);
    }
}
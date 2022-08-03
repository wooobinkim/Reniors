package com.common.jmark.common.exception;

public class NotFoundException extends RuntimeException {

    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리입니다.";
    public static final String AWARD_NOT_FOUND = "존재하지 않는 수상경력입니다.";
    public static final String CAREER_DETAIL_NOT_FOUND = "존재하지 않는 경력사항입니다.";
    public static final String LICENSE_NOT_FOUND = "존재하지 않는 자격증입니다.";
    public static final String RECOMMEND_CONDITION_NOT_FOUND = "존재하지 않는 추천 조건입니다.";

    public NotFoundException(String message) {
        super(message);
    }
}
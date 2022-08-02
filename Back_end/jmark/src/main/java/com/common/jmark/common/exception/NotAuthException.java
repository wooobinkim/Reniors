package com.common.jmark.common.exception;

public class NotAuthException extends RuntimeException{

    public static final String COMPANY_NOT_MATCH = "접근 권한이 없는 회사입니다.";

    public NotAuthException() {
        super();
    }

    public NotAuthException(String message) {
        super(message);
    }
}

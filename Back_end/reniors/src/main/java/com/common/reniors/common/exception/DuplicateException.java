package com.common.reniors.common.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(){
        super();
    }
    public DuplicateException(String message){
        super(message);
    }
}
